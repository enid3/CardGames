package com.github.enid3.cardgames.game.lib.engine.components;

import com.github.enid3.cardgames.game.lib.engine.components.card.Card;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Hand {
    private List<Card> cards;

    public Hand() {
        this(new ArrayList<>());
    }
    public Hand(List<Card> dealCards) {
        this.cards = dealCards;
    }
}
