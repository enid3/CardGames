package com.github.enid3.cardgames.webapp.service.game.provider;

import com.github.enid3.cardgames.game.lib.engine.state.GameState;

public interface GameStateProvider {
    GameState getGameState(long gameId);
}
