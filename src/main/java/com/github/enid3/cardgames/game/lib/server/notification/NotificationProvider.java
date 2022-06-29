package com.github.enid3.cardgames.game.lib.server.notification;


import com.github.enid3.cardgames.game.lib.common.Event;
import com.github.enid3.cardgames.game.lib.common.Identifiable;

public interface NotificationProvider {
    void notify(Identifiable target, Event event);
    void notifyAll(Iterable<Identifiable> targets, Event event);
}
