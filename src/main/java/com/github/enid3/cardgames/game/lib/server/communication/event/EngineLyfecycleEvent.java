package com.github.enid3.cardgames.game.lib.server.communication.event;

import com.github.enid3.cardgames.game.lib.common.Event;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EngineLyfecycleEvent implements Event {
    private final EventType type;
}
