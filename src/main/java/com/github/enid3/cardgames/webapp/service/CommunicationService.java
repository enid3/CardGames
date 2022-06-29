package com.github.enid3.cardgames.webapp.service;

import com.github.enid3.cardgames.game.lib.common.Identifiable;

public interface CommunicationService {
    void sendTo(long userId, Object data);
    void receiveFrom(long gameId, long userId, Object data);
}
