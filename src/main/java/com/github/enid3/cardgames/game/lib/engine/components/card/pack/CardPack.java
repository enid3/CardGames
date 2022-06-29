package com.github.enid3.cardgames.game.lib.engine.components.card.pack;

import com.github.enid3.cardgames.game.lib.engine.components.card.Rank;
import com.github.enid3.cardgames.game.lib.engine.components.card.Suit;

/**
 * Abstraction, for deck size(e.g. 52, 36, ...) and  so on.
 */
public interface CardPack {
    Rank[] getRanks();
    Suit[] getSuits();
}
