package com.github.enid3.cardgames.game.impl.cardcomparsion;

import com.github.enid3.cardgames.game.lib.engine.components.Hand;

import java.util.Collections;
import java.util.Comparator;

public class MaxCardInHandComparator implements Comparator<Hand> {
    @Override
    public int compare(Hand h1, Hand h2) {
        return Collections.max(h1.getCards())
                .compareTo(Collections.max(h2.getCards()));
    }
}
