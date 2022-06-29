package com.github.enid3.cardgames.webapp.service.game.management;

import com.github.enid3.cardgames.game.lib.engine.entity.Player;

import java.util.Collection;

public interface GameLifecycleManagementService {
    long create(String gameName, Collection<Player> players);
    void start(long gameId);
    void resume(long gameId);
    void pause(long gameId);
    void stop(long gameId);
    void destroy(long gameId);
}
