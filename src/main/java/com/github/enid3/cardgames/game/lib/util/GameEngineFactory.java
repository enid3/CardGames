package com.github.enid3.cardgames.game.lib.util;

import com.github.enid3.cardgames.game.lib.engine.GeneralGameEngine;
import com.github.enid3.cardgames.game.lib.engine.configuration.Configuration;
import com.github.enid3.cardgames.game.lib.engine.entity.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class GameEngineFactory {
    private final ApplicationContext ac;
    GeneralGameEngine getEngine() {
        //ac.getBean
        return null;
    }

    public Collection<String> getAvailableGameEngines() {
        return ac.getBeansOfType(GeneralGameEngine.class).keySet();
    }

    public static GeneralGameEngine of(Class<? extends GeneralGameEngine> engineClass) throws ReflectiveOperationException{
        GeneralGameEngine ge;
        Constructor<? extends GeneralGameEngine> engineClassConstructor = engineClass.getConstructor();
        ge = engineClassConstructor.newInstance();
        return ge;
    }

    public static void prepare(GeneralGameEngine ge, Collection<Player> players, Configuration configuration) {
        // TODO fix: provide more clear GeneralGameEngine initialization API sequence
        ge.configure(configuration);
        ge.init();
        ge.joinPlayers(players);

    }

}
