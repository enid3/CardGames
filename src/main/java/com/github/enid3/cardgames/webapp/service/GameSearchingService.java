package com.github.enid3.cardgames.webapp.service;

import com.github.enid3.cardgames.webapp.data.dto.JoinQueueDTO;
import com.github.enid3.cardgames.webapp.data.dto.JoinQueueResponseDTO;
import com.github.enid3.cardgames.webapp.data.dto.LeaveQueueResponseDTO;

/**
 * Purpose of Math {@link GameSearchingService} is
 * game room's management: creating, searching, providing
 * information, etc.
 *
 */
public interface GameSearchingService {
    JoinQueueResponseDTO joinQueue(long userId, JoinQueueDTO dto);
    LeaveQueueResponseDTO leaveQueue(long userId, JoinQueueDTO dto);
}
