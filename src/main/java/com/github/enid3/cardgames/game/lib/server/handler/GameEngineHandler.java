package com.github.enid3.cardgames.game.lib.server.handler;

import com.github.enid3.cardgames.game.lib.common.Identifiable;
import com.github.enid3.cardgames.game.lib.engine.state.GameState;

public interface GameEngineHandler extends Runnable {
    void handlePlayerStrategy (Identifiable playerId, String strategy);
    GameState getGameState();

}
