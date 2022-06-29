package com.github.enid3.cardgames.game.impl.cardcomparsion;

import com.github.enid3.cardgames.game.lib.engine.components.Hand;
import com.github.enid3.cardgames.game.lib.engine.components.card.pack.CardPack;
import com.github.enid3.cardgames.game.lib.engine.configuration.Configuration;
import lombok.Builder;
import lombok.Data;

import java.util.Comparator;

/**
 * Reprents configuration for {@link CardComparisonGameEngine}
 *
 */
@Data
@Builder
public class CardComparisonGameConfiguration implements Configuration {
    /**
     * {@link CardPack} if used to create
     * {@link com.github.enid3.cardgames.game.lib.engine.components.Deck}
     * instance.
     */
    private CardPack cardPack;

    /**
     * Represent hand size of {@link CardComparisonGamePlayer}
     */
    private int handSize;

    /**
     * {@link CardComparisonGameConfiguration#handComparator} used
     * for choosing winner. Player's with the highest value
     * will be considered as winners.
     */
    private Comparator<Hand> handComparator;
    /**
     * The value {@link CardComparisonGameConfiguration#allFoldFine},
     * that will be taken away, in case all players are using
     * {@link CardComparisonGameEngine.GameStrategy#FOLD} strategy.
     * @see CardComparisonGameEngine for more details.
     */
    @Builder.Default
    private long allFoldFine = 1;
    /**
     * The value {@link CardComparisonGameConfiguration#allFoldFine}
     * will be taken away form all,
     * {@link CardComparisonGameEngine.GameStrategy#FOLD}-strategy players,
     * in case there is at least one player with
     * {@link CardComparisonGameEngine.GameStrategy#PLAY} strategy.
     * @see CardComparisonGameEngine for more details.
     */
    @Builder.Default
    private long foldFine = 3;

    /**
     * The value {@link CardComparisonGameConfiguration#allFoldFine}
     * will be taken away form all,
     * {@link CardComparisonGameEngine.GameStrategy#FOLD}-strategy players,
     * in case there is at least one player with
     * {@link CardComparisonGameEngine.GameStrategy#PLAY} strategy.
     * @see CardComparisonGameEngine for more details.
     */
    @Builder.Default
    private long bet = 10;
}
