package com.github.enid3.cardgames.game.lib.engine.components.card.pack.french;

import com.github.enid3.cardgames.game.lib.engine.components.card.Rank;
import com.github.enid3.cardgames.game.lib.engine.components.card.Suit;
import com.github.enid3.cardgames.game.lib.engine.components.card.pack.CardPack;
import com.github.enid3.cardgames.game.lib.util.GameHandlerFactory;

public class FrenchPack implements CardPack {
    private static FrenchPack frenchPack;
    private FrenchPack() {}
    @Override
    public Rank[] getRanks() {
        return FrenchRank.values();
    }

    @Override
    public Suit[] getSuits() {
        return FrenchSuit.values();
    }
    public static FrenchPack instance()  {
        if(frenchPack == null) {
            frenchPack = new FrenchPack();
        }
        return frenchPack;
    }

    private static class InstanceHolder {
        private static final FrenchPack instance = new FrenchPack();
    }

    public static FrenchPack getInstance() {
        return InstanceHolder.instance;
    }

}
