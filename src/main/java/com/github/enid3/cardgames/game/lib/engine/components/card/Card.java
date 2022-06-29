package com.github.enid3.cardgames.game.lib.engine.components.card;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Getter
public class Card implements Comparable<Card> {
    private final Suit suit;
    private final Rank rank;

    @Override
    public int compareTo(Card card) {
        return this.getRank().compareTo(card.getRank());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit.equals(card.suit) && rank.equals(card.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }
}
