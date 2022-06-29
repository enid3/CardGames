package com.github.enid3.cardgames.webapp.controller.api.v1;

import com.github.enid3.cardgames.game.lib.util.GameHandlerFactory;
import com.github.enid3.cardgames.webapp.data.dto.JoinQueueDTO;
import com.github.enid3.cardgames.webapp.data.dto.JoinQueueResponseDTO;
import com.github.enid3.cardgames.webapp.data.dto.LeaveQueueResponseDTO;
import com.github.enid3.cardgames.webapp.data.entity.Identifiable;
import com.github.enid3.cardgames.webapp.service.GameSearchingService;
import com.github.enid3.cardgames.game.lib.util.GameEngineFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/game")
@RequiredArgsConstructor
@Slf4j
public class GameSearchingController {
    private final GameHandlerFactory gameHandlerFactory;
    private final GameSearchingService gameSearchingService;

    /**
     * Join queue for specific game type
     * @param id authentication principle of player
     * @param dto  data for joining queue(GameName ...)
     * @see JoinQueueDTO
     * @return information about, current queue state, id of used server,...
     * @see JoinQueueResponseDTO
     */
    @PostMapping
    JoinQueueResponseDTO joinQueue(@AuthenticationPrincipal Identifiable id, @RequestBody JoinQueueDTO dto) {
        return gameSearchingService.joinQueue(id.getId(), dto);
    }

    /**
     * Leave queue
     * @param id authentication principle of player
     * @param dto  data for leaving queue(Game name, ...)
     * @see JoinQueueDTO
     * @see LeaveQueueResponseDTO
     */
    @DeleteMapping
    void leaveQueue(@AuthenticationPrincipal Identifiable id, @RequestBody JoinQueueDTO dto) {
        gameSearchingService.leaveQueue(id.getId(), dto);
    }

    /**
     * Return list of available game engine names
     * @return collection of game engine names
     */
    @GetMapping
    public Collection<String> getAvailableGameEngines() {
        return gameHandlerFactory.getAvailableGames();
    }

}
