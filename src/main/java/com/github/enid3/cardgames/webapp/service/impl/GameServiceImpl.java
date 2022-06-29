package com.github.enid3.cardgames.webapp.service.impl;

import com.github.enid3.cardgames.game.lib.server.notification.NotificationProvider;
import com.github.enid3.cardgames.webapp.data.dto.TurnResponseDTO;
import com.github.enid3.cardgames.game.lib.common.Identifiable;
import com.github.enid3.cardgames.game.lib.engine.entity.Player;
import com.github.enid3.cardgames.game.lib.engine.state.GameState;
import com.github.enid3.cardgames.game.lib.server.handler.GameEngineHandler;
import com.github.enid3.cardgames.game.lib.util.GameHandlerFactory;
import com.github.enid3.cardgames.webapp.service.brocker.IdentifiableBrocker;
import com.github.enid3.cardgames.webapp.service.exception.ServiceException;
import com.github.enid3.cardgames.webapp.service.game.GameService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.naming.OperationNotSupportedException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private static long GAME_HANDLER_ID;
    private final Map<String, Long> reservedIds = Collections.synchronizedMap(new HashMap<>());
    private Map<Long, GameEngineHandler> games = Collections.synchronizedMap(new HashMap<>());


    private final Executor executor;
    private final GameHandlerFactory gameHandlerFactory;

    private final BeanFactory beanFactory;
    private final IdentifiableBrocker identifiableBrocker;

    @PostConstruct
    private void init() {
        Collection<String> availableGames = getAvailableGames();
        for(String gameName : availableGames) {
            reservedIds.put(gameName, GAME_HANDLER_ID++);
        }
    }

    protected GameEngineHandler getGameHandler(long id) throws ServiceException {
        GameEngineHandler handler = games.get(id);
        if(handler != null) {
            return games.get(id);
        }
        throw new ServiceException("No game with id: " + id);
    }

    private TurnResponseDTO turn(long gameId, Identifiable id, String strategy) {
        GameEngineHandler handler;
        synchronized (games) {
            handler = getGameHandler(gameId);
        }
        handler.handlePlayerStrategy(id, strategy);
        // TODO fix: wait turn handled
        return new TurnResponseDTO(getGameState(gameId));
    }

    @Override
    public TurnResponseDTO turn(long gameId, long playerId, String strategy) {
        return turn(gameId, identifiableBrocker.id(playerId), strategy);
    }

    @Override
    public TurnResponseDTO turn(long gameId, long playerId, long modifier, String strategy) {
        return turn(gameId, identifiableBrocker.id(playerId, modifier), strategy);
    }

    @Override
    @SneakyThrows
    public long create(String gameName, Collection<Player> players) {
        synchronized (games) {
            GameEngineHandler handler = gameHandlerFactory.of(
                    gameName,
                    players,
                    beanFactory.getBean(NotificationProvider.class));
            long id = reserveNewId(gameName);
            games.put(id, handler);
            return id;
        }
    }

    @Override
    public void start(long gameId) {
        synchronized (games) {
            GameEngineHandler handler = getGameHandler(gameId);
            executor.execute(handler);
        }
    }

    @Override
    @SneakyThrows
    public void resume(long gameId) {
        throw new OperationNotSupportedException("Game pausing/resuming not supported");
    }

    @Override
    @SneakyThrows
    public void pause(long gameId) {
        throw new OperationNotSupportedException("Game pausing/resuming not supported");
    }

    @Override
    @SneakyThrows
    public void stop(long gameId) {
        throw new OperationNotSupportedException("Game stopping not supported");
    }

    @Override
    @SneakyThrows
    public void destroy(long gameId) {
        throw new OperationNotSupportedException("Game destroying not supported");
    }

    @Override
    public GameState getGameState(long gameId) {
        synchronized (games) {
            GameEngineHandler handler = getGameHandler(gameId);
            return handler.getGameState();
        }
    }

    @Override
    public Collection<String> getAvailableGames() {
        return gameHandlerFactory.getAvailableGames();
    }

    @Override
    public long getNextGameId(String name) {
        synchronized (reservedIds) {
            return reservedIds.get(name);
        }
    }

    private long reserveNewId(String name) {
        synchronized (reservedIds) {
            long id = getNextGameId(name);
            reservedIds.put(name, GAME_HANDLER_ID++);
            return id;
        }
    }
}
