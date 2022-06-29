package com.github.enid3.cardgames.webapp.service.impl;

import com.github.enid3.cardgames.webapp.data.dto.JoinQueueDTO;
import com.github.enid3.cardgames.webapp.data.dto.JoinQueueResponseDTO;
import com.github.enid3.cardgames.webapp.data.dto.LeaveQueueResponseDTO;
import com.github.enid3.cardgames.game.lib.engine.entity.Player;
import com.github.enid3.cardgames.webapp.data.entity.GameStatus;
import com.github.enid3.cardgames.webapp.service.GameSearchingService;
import com.github.enid3.cardgames.webapp.service.brocker.PlayerBrocker;
import com.github.enid3.cardgames.webapp.service.exception.ServiceException;
import com.github.enid3.cardgames.webapp.service.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GameSearchingServiceImpl implements GameSearchingService {
    // TODO fix: provide gameSize in not hard-coded manner
    private final static int GAME_SIZE = 13;

    private final GameService gameService;
    private final PlayerBrocker playerBrocker;
    //private Queue<JoinGameDTO> mathMakingQueue = new LinkedList<>();

    private final Map<String, Queue<Player>> searchingQueues;

    @PostConstruct
    private void init() {
        Collection<String> availableGames = gameService.getAvailableGames();
        for(String gameName : availableGames) {
            searchingQueues.put(gameName, new LinkedList<>());
        }

    }
    protected @NotNull Queue<Player> getPlayersQueue(String game) {
        Queue<Player> players = searchingQueues.get(game);
        if(players != null) {
            return players;
        }
        throw new ServiceException("No game with name: " + game);
    }

    @Override
    public synchronized JoinQueueResponseDTO joinQueue(long userId, JoinQueueDTO dto) {
        Player player = playerBrocker.reserve(userId, dto.getTokens());
        String game = dto.getGame();
        Queue<Player> players;
        Queue<Player> playersCopy;
        long nextGameId;
        GameStatus gameStatus = GameStatus.FORMING;
        int gameSize = GAME_SIZE;//gameInfoProvider.getGameSize(game);
        synchronized (this) {
            players = getPlayersQueue(game);
            players.add(player);
            nextGameId = gameService.getNextGameId(game);
            playersCopy = new LinkedList<>(players);

            if(gameSize <= players.size()) {
                launchGame(players, game, gameSize);
                gameStatus = GameStatus.STARTED;
            }

        }

        // TODO fix: use builder
        // TODO fix: gameStatus.FORMING -> 206
        // TODO fix: gameStatus.STARTED -> 201 + Content-Location: ...
        return new JoinQueueResponseDTO(nextGameId, game, gameStatus, playersCopy, gameSize);
    }

    @Override
    public LeaveQueueResponseDTO leaveQueue(long userId, JoinQueueDTO dto) {
        Player player = playerBrocker.cast(userId);
        String game = dto.getGame();
        synchronized (this) {
            Queue<Player> players = getPlayersQueue(game);

            if (!players.remove(player)) {
                throw new ServiceException("No such player(" + userId + ") in queue for: " + dto.getGame());
            }
        }
        return new LeaveQueueResponseDTO();
    }

    public long launchGame(Queue<Player> players, String gameName, int gameSize)  {
        List<Player> playersForGame = new ArrayList<>();
        synchronized (this) {
            while(playersForGame.size() < gameSize) {
                playersForGame.add(players.remove());
            }
            long gameId = gameService.create(gameName, playersForGame);
            gameService.start(gameId);
            return gameId;

        }
    }

}
