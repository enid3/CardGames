package com.github.enid3.cardgames.game.lib.util;

import com.github.enid3.cardgames.game.lib.engine.GeneralGameEngine;
import com.github.enid3.cardgames.game.lib.engine.configuration.Configuration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EngineConfigurationHolder {
    private final Class<? extends GeneralGameEngine> engineClass;
    private final Configuration configuration;
}
