package com.github.enid3.cardgames.webapp.service.impl;

import com.github.enid3.cardgames.game.lib.common.Event;
import com.github.enid3.cardgames.game.lib.common.Identifiable;
import com.github.enid3.cardgames.game.lib.server.notification.AbstractNotificationProvider;
import com.github.enid3.cardgames.webapp.service.CommunicationService;
import com.github.enid3.cardgames.webapp.service.brocker.IdentifiableBrocker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationProviderAdapter extends AbstractNotificationProvider {
    private final CommunicationService communicationService;
    private final IdentifiableBrocker idBrocker;

    @Override
    public void notify(Identifiable target, Event event) {
        communicationService.sendTo(idBrocker.id(target), event);

    }
}
