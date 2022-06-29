package com.github.enid3.cardgames.webapp.service.game.provider;

import com.github.enid3.cardgames.game.lib.server.handler.GameEngineHandler;

public interface GameHandlerProviderService {
    GameEngineHandler getGameHandler(String name);
    //GameEngineHandler getGameHandler(String name);
}
