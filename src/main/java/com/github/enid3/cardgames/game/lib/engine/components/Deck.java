package com.github.enid3.cardgames.game.lib.engine.components;

import com.github.enid3.cardgames.game.lib.engine.components.card.Card;
import com.github.enid3.cardgames.game.lib.engine.components.card.Rank;
import com.github.enid3.cardgames.game.lib.engine.components.card.Suit;
import com.github.enid3.cardgames.game.lib.engine.components.card.pack.CardPack;

import java.util.*;

public class Deck extends AbstractQueue<Card> {
    private Queue<Card> cards;
    private Deck(Queue<Card> cards) {
        this.cards = cards;
    }

    public static Deck of(CardPack pack) {
        // TODO fix: static Card's to optimize memory usage
        LinkedList<Card> cards = new LinkedList<>();
        for(Rank r : pack.getRanks()) {
            for(Suit s : pack.getSuits()) {
                cards.add(new Card(s, r));
            }
        }
        Collections.shuffle(cards);
        return new Deck(cards);
    }
    public List<Card> dealCards(int count) {
        List<Card> toDeal = new LinkedList<>();
        while(toDeal.size() < count) toDeal.add(cards.poll());
        return toDeal;
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public int size() {
        return cards.size();
    }

    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public boolean offer(Card card) {
        return cards.offer(card);
    }

    @Override
    public Card poll() {
        return cards.poll();
    }

    @Override
    public Card peek() {
        return cards.peek();
    }
}
