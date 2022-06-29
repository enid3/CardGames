package com.github.enid3.cardgames.webapp.data.dto;

import com.github.enid3.cardgames.game.lib.engine.entity.Player;
import com.github.enid3.cardgames.webapp.data.entity.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
import java.util.List;



@Data
@AllArgsConstructor
public class JoinQueueResponseDTO {
    private long gameId;
    private String gameName;
    private GameStatus status;
    private Collection<Player> players;
    private int gameSize;
    // TODO provide configuration in JoinQueueResponseDTO (or in other place)
    //private Configuration configuration;
}
