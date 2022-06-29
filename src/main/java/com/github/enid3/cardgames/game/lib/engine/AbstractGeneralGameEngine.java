package com.github.enid3.cardgames.game.lib.engine;

import com.github.enid3.cardgames.game.lib.common.Identifiable;
import com.github.enid3.cardgames.game.lib.engine.configuration.Configuration;
import com.github.enid3.cardgames.game.lib.engine.entity.Player;
import com.github.enid3.cardgames.game.lib.engine.entity.Strategy;
import com.github.enid3.cardgames.game.lib.engine.util.PlayerUtils;
import com.github.enid3.cardgames.game.lib.engine.state.AbstractEngineState;
import lombok.SneakyThrows;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * {@link AbstractGeneralGameEngine} is used to simplify
 * implementing of {@link GeneralGameEngine}.
 *
 * @param <P> type of player used by the game.
 * @param <S> type of strategya used by the game.
 */
public abstract class AbstractGeneralGameEngine<P extends Player, S extends Strategy> implements GeneralGameEngine {
    protected Function<Player, P> playerMapper;
    protected Function<String, Strategy> strategyMapper;
    protected Map<Identifiable, P> players;
    protected Iterable<Identifiable> winners;
    protected Map<P, Strategy> playerStrategies;


    public AbstractGeneralGameEngine(Function<Player, P> playerMapper,
                                     Function<String, Strategy> strategyMapper) {
        this.playerMapper = playerMapper;
        this.strategyMapper = strategyMapper;
    }
    @Override
    public void prepareRound() {
        for(P p : this.players.values()) {
            preparePlayerForRound(p);
        }
        playerStrategies.clear();
    }
    protected abstract void preparePlayerForRound(P player);
    @Override
    @SneakyThrows
    public void handleTurn(Identifiable playerId, String strategy, Object... args) {
        Strategy strat = strategyMapper.apply(strategy);
        if(validate(playerId, strat, args)) {
            playerStrategies.put(players.get(playerId), strat);
        } else {
            throw new Exception("Turn not allowed");
        }
    }

    // TODO are args actually needed, since Strategy can be extended?
    /**
     * Validating turn sequence.
     * @param playerId {@link Identifiable} of player, that performs turn.
     * @param strategy {@link Strategy}, used by player for this turn.
     * @param args extra data, in case strategy requires params.
     * @return is sequence and strategy is valid?
     */
    protected abstract boolean validate(Identifiable playerId, Strategy strategy, Object... args);

    @Override
    public AbstractEngineState<P> getState() {
        return new AbstractEngineState<P>(
                players.values(),
                PlayerUtils.map(playerStrategies, p -> p),
                winners );
    }

    @Override
    public void joinPlayers(Collection<? extends Player> players) {
        this.players = PlayerUtils.toMap(players, playerMapper);
        if(players.size() != this.players.size()) {
            throw new RuntimeException("Duplicated players are not allowed");
        }
    }

    @Override
    public void clear() {
        init();
    }

    @Override
    public void init() {
        this.winners = null;
        this.players = new HashMap<>();
        this.playerStrategies = new HashMap<>();
    }

    @Override
    public void configure(Configuration configuration) {

    }
    protected void setWinners(Iterable<Identifiable> winners) {
        this.winners = winners;
    }

    @Override
    public Iterable<Identifiable> getWinners() {
        return this.winners;
    }
}
