package com.github.enid3.cardgames.game.lib.server.notification;

import com.github.enid3.cardgames.game.lib.common.Event;
import com.github.enid3.cardgames.game.lib.common.Identifiable;

public abstract class AbstractNotificationProvider implements NotificationProvider {
    @Override
    public void notifyAll(Iterable<Identifiable> targets, Event event) {
        targets.forEach((id) -> notify(id, event));
    }
}
