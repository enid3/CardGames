package com.github.enid3.cardgames.webapp.data.dto;

import com.github.enid3.cardgames.game.lib.engine.state.GameState;
import com.github.enid3.cardgames.webapp.data.entity.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class TurnResponseDTO {
    private GameState state;
}
