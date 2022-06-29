package com.github.enid3.cardgames.game.impl.cardcomparsion;

import com.github.enid3.cardgames.game.lib.engine.components.Deck;
import com.github.enid3.cardgames.game.lib.engine.state.AbstractEngineState;
import lombok.*;

/**
 * {@link CardComparisonGameState} represents the state of
 * {@link CardComparisonGameEngine}.
 *
 * @see AbstractEngineState for more details.
 */
@Getter
public class CardComparisonGameState extends AbstractEngineState<CardComparisonGamePlayer> {
    protected Deck deck;
    public CardComparisonGameState(AbstractEngineState<CardComparisonGamePlayer> state, Deck deck){
        super(state);
        this.deck = deck;
    }
}
