package com.github.enid3.cardgames.game.impl.cardcomparsion;

import com.github.enid3.cardgames.game.lib.engine.AbstractGeneralGameEngine;
import com.github.enid3.cardgames.game.lib.engine.components.Deck;
import com.github.enid3.cardgames.game.lib.engine.components.Hand;
import com.github.enid3.cardgames.game.lib.engine.entity.Strategy;
import com.github.enid3.cardgames.game.lib.common.Identifiable;
import com.github.enid3.cardgames.game.lib.engine.configuration.Configuration;
import com.github.enid3.cardgames.game.lib.engine.entity.Player;
import com.github.enid3.cardgames.game.lib.engine.util.PlayerUtils;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.*;
import java.util.stream.Collectors;

/**
 * {@link CardComparisonGameEngine} is a configurable solution, which one
 * generalize 'single-card-game', 'double-card-game'.
 * In case, there is at least one player with {@link CardComparisonGameEngine.GameStrategy#PLAY}
 * the bank for winners will be formed. Those, who {@link CardComparisonGameEngine.GameStrategy#PLAY}
 * will provide {@link CardComparisonGameConfiguration#getBet()}, {@link CardComparisonGameEngine.GameStrategy#FOLD}-ers
 * --- {@link CardComparisonGameConfiguration#getFoldFine()}.
 * Otherwise everyone will loose {@link CardComparisonGameConfiguration#getAllFoldFine()}.
 */
@Getter
public class CardComparisonGameEngine extends AbstractGeneralGameEngine<CardComparisonGamePlayer, CardComparisonGameEngine.GameStrategy> {
    protected Deck deck;
    protected Comparator<CardComparisonGamePlayer> playerComparator;
    protected CardComparisonGameConfiguration conf;

    protected int turnsLeft;

    @Override
    public void configure(Configuration configuration) {
        super.configure(configuration);
        if(configuration instanceof CardComparisonGameConfiguration) {
            this.conf = (CardComparisonGameConfiguration)  configuration;
        } else {
            throw new RuntimeException("Providing wrong type of configuration");
        }
    }
    public CardComparisonGameEngine() {
        super(CardComparisonGamePlayer::new, (s) -> Enum.valueOf(GameStrategy.class, s));
    }

    public enum GameStrategy implements Strategy {
        PLAY, FOLD;
    }


    @Override
    public void prepareRound() {
        super.prepareRound();
        turnsLeft = players.size();
    }

    @Override
    public boolean anyTurnsLeft() {
        return this.turnsLeft > 0;
    }

    @Override
    protected void preparePlayerForRound(CardComparisonGamePlayer player) {
        player.setHand(new Hand(deck.dealCards(conf.getHandSize())));
    }

    @Override
    public void handleTurn(Identifiable playerId, String strategy, Object... args) {
        super.handleTurn(playerId, strategy, args);
        --turnsLeft;
    }

    @Override
    protected boolean validate(Identifiable playerId, Strategy strategy, Object... args) {
        return strategy instanceof GameStrategy;
    }

    @Override
    public CardComparisonGameState getState() {
        return new CardComparisonGameState(super.getState(), deck);
    }

    @Override
    public void joinPlayers(Collection<? extends Player> players) {
        super.joinPlayers(players);
        this.turnsLeft = this.players.size();
    }

    @Override
    public void init() {
        super.init();
        this.deck = Deck.of(conf.getCardPack());
        this.turnsLeft = this.players.size();
    }

    @Override
    @SneakyThrows
    public void finishRound() {
        Map<Strategy, List<CardComparisonGamePlayer>> playersByStrategy = PlayerUtils.groupPlayersByStrategy(this.playerStrategies);
        List<CardComparisonGamePlayer> playPlayers = playersByStrategy.getOrDefault(GameStrategy.PLAY, List.of());
        List<CardComparisonGamePlayer> foldPlayers = playersByStrategy.getOrDefault(GameStrategy.FOLD, List.of());

        if(playPlayers.size() != 0) {
            long bankSize =  conf.getBet() * playPlayers.size()
                    + conf.getFoldFine() * foldPlayers.size();

            Hand winnersHand = playPlayers.stream()
                    .map(CardComparisonGamePlayer::getHand)
                    .max(conf.getHandComparator())
                    .orElseThrow();

            List<CardComparisonGamePlayer> winners = playPlayers.stream()
                    .filter(p -> p.getHand().equals(winnersHand))
                    .collect(Collectors.toList());

            this.setWinners(PlayerUtils.map(winners, p -> p));

            // TODO should differences be provided here?
            PlayerUtils.applyPayoff(playPlayers, -conf.getBet());
            PlayerUtils.applyPayoff(foldPlayers, -conf.getFoldFine());

            PlayerUtils.applyPayoff(winners, bankSize / winners.size());



        } else {
            PlayerUtils.applyPayoff(players.values(), conf.getAllFoldFine());
        }

        //prepareRound();
    }

    @Override
    public boolean anyRoundsLeft() {
        return !deck.isEmpty();
    }
    
}
