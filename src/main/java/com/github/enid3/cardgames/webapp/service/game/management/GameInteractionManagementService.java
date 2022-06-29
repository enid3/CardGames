package com.github.enid3.cardgames.webapp.service.game.management;

import com.github.enid3.cardgames.webapp.data.dto.TurnResponseDTO;

public interface GameInteractionManagementService {

    TurnResponseDTO turn(long gameId, long playerId, String strategy);
    TurnResponseDTO turn(long gameId, long playerId, long modifier, String strategy);
}
