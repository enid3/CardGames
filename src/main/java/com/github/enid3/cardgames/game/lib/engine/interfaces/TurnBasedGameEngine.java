package com.github.enid3.cardgames.game.lib.engine.interfaces;

import com.github.enid3.cardgames.game.lib.common.Identifiable;

public interface TurnBasedGameEngine {
    boolean anyTurnsLeft();
    void handleTurn(Identifiable playerId, String strategy, Object... args);
}
