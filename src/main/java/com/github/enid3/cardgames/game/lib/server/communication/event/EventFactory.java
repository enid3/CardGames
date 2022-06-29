package com.github.enid3.cardgames.game.lib.server.communication.event;

public class EventFactory {
    public EngineLyfecycleEvent of(EventType type) {
        return new EngineLyfecycleEvent(type);
    }
}
