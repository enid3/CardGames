package com.github.enid3.cardgames.webapp.controller.api.v1;

import com.github.enid3.cardgames.game.lib.engine.state.GameState;
import com.github.enid3.cardgames.webapp.data.dto.TurnDTO;
import com.github.enid3.cardgames.webapp.data.dto.TurnResponseDTO;
import com.github.enid3.cardgames.webapp.data.entity.Identifiable;
import com.github.enid3.cardgames.webapp.service.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/game/{gameId}")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    /**
     * Performs turn
     * @param id user identifier
     * @param gameId identifier of the game
     * @param dto data, that represents turn
     * @return game state (it may not contain result of turn)
     */
    // TODO fix: return of turn() must represent result of turn
    @PostMapping("/turn")
    public TurnResponseDTO turn(@AuthenticationPrincipal Identifiable id,
                                @PathVariable long gameId,
                                @RequestBody TurnDTO dto) {
        return gameService.turn(gameId, id.getId(), dto.getStrategy());
    }

    /**
     * Provides GameState for
     * @param gameId id of required game
     * @return GameState of specified game
     *
     */
    @GetMapping
    public GameState getGameState(@PathVariable long gameId) {
        return  gameService.getGameState(gameId);
    }
}
