package com.github.enid3.cardgames.webapp.service.impl;

import com.github.enid3.cardgames.webapp.data.dto.TurnDTO;
import com.github.enid3.cardgames.webapp.service.CommunicationService;
import com.github.enid3.cardgames.webapp.service.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunicationServiceImpl implements CommunicationService {
    private final GameService gameService;

    @Override
    public void sendTo(long userId, Object data) { }

    @Override
    public void receiveFrom(long gameId, long userId, Object data) {
        TurnDTO dto = (TurnDTO) data;
        gameService.turn(gameId, userId, dto.getStrategy());
    }
}
