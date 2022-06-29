package com.github.enid3.cardgames.game.lib.engine.configuration;

import com.github.enid3.cardgames.game.lib.engine.GeneralGameEngine;

import java.util.List;

public interface GameEngineConfigurationProvider {
    Configuration of(String name);
    List<Configuration> available(GeneralGameEngine gameEngine);
}
