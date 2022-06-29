package com.github.enid3.cardgames.game.lib.engine;

import com.github.enid3.cardgames.game.lib.engine.configuration.ConfigurableEngine;
import com.github.enid3.cardgames.game.lib.engine.interfaces.*;
import com.github.enid3.cardgames.game.lib.engine.interfaces.RoundBasedGameEngine;
import com.github.enid3.cardgames.game.lib.engine.interfaces.provider.StateProvider;
import com.github.enid3.cardgames.game.lib.engine.interfaces.provider.WinnersProvider;

/**
 * It's a header interface, that provides
 * abstraction for game engines, handled by this library.
 */
public interface GeneralGameEngine extends
        RoundBasedGameEngine,
        TurnBasedGameEngine,
        StateProvider,
        ReusableGameEngine,
        ConfigurableEngine,
        WinnersProvider {
}
