package com.github.enid3.cardgames.game.lib;

import com.github.enid3.cardgames.game.lib.engine.GeneralGameEngine;

public interface GameInfoProvider {
    int getGameSize(String game);
    Class<? extends GeneralGameEngine> getGameEngineClass(String game);

}
