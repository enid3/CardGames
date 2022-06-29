package com.github.enid3.cardgames.game.lib.util;

import com.github.enid3.cardgames.game.lib.engine.GeneralGameEngine;
import com.github.enid3.cardgames.game.lib.engine.configuration.Configuration;
import com.github.enid3.cardgames.game.lib.engine.entity.Player;
import com.github.enid3.cardgames.game.lib.server.communication.event.EventFactory;
import com.github.enid3.cardgames.game.lib.server.handler.GameEngineHandler;
import com.github.enid3.cardgames.game.lib.server.handler.impl.NotifyingGameEngineHandler;
import com.github.enid3.cardgames.game.lib.server.notification.NotificationProvider;

import java.util.*;

public class GameHandlerFactory {
    private GameHandlerFactory() {
        this.engines = new HashMap<>();
    }

    private final Map<String, EngineConfigurationHolder> engines;

    private final EventFactory eventFactory = new EventFactory();

    // TODO implementation: add DI support(JSR-299, to get around spring dependency in game.* )
    public void register(String name, Class<? extends GeneralGameEngine> engine, Configuration configuration) {
        this.engines.put(name, new EngineConfigurationHolder(engine,configuration));
    }


    public GameEngineHandler of(GeneralGameEngine ge, NotificationProvider notificationProvider )
            throws ReflectiveOperationException {
        return new NotifyingGameEngineHandler(ge, notificationProvider, eventFactory);
    }

    public GameEngineHandler of(String gameName,
                                Collection<Player> players,
                                NotificationProvider notificationProvider)
            throws ReflectiveOperationException {
        EngineConfigurationHolder confHolder = this.engines.get(gameName);
        Class<? extends GeneralGameEngine> clazz = confHolder.getEngineClass();
        Configuration conf = confHolder.getConfiguration();

        GeneralGameEngine ge = GameEngineFactory.of(clazz);
        GameEngineFactory.prepare(ge, players, conf);

        return this.of(ge, notificationProvider);
    }

    public Collection<String> getAvailableGames() {
        return engines.keySet();
    }

    private static class InstanceHolder {
        private static final GameHandlerFactory instance = new GameHandlerFactory();
    }

    public static GameHandlerFactory getInstance() {
        return InstanceHolder.instance;
    }

}
