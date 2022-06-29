package com.github.enid3.cardgames.game.lib.server.handler.impl;

import com.github.enid3.cardgames.game.lib.common.Identifiable;
import com.github.enid3.cardgames.game.lib.engine.GeneralGameEngine;
import com.github.enid3.cardgames.game.lib.server.communication.event.EventFactory;
import com.github.enid3.cardgames.game.lib.server.communication.event.EventType;
import com.github.enid3.cardgames.game.lib.server.notification.NotificationProvider;

import java.util.Collections;
import java.util.concurrent.LinkedBlockingQueue;

public class NotifyingGameEngineHandler extends AbstractGameEngineHandler {
    private final NotificationProvider notificationProvider;
    private final EventFactory eventFactory;

    public NotifyingGameEngineHandler(
            GeneralGameEngine gameEngine,
            NotificationProvider notificationProvider,
            EventFactory eventFactory ) {
        super(gameEngine, new LinkedBlockingQueue<>());
        this.notificationProvider = notificationProvider;
        this.eventFactory = eventFactory;
    }
    protected void notify(Identifiable target, EventType type) {
        notificationProvider.notify(target, eventFactory.of(type));
    }
    protected void notifyAll(EventType type) {
        // TODO fix: notifyAll( Collections.EMPTY_LIST, ... )
        notificationProvider.notifyAll( Collections.EMPTY_LIST, eventFactory.of(type));
    }

    @Override
    protected void onGameStarted() {
        notifyAll(EventType.GAME_STARTED);
    }

    @Override
    protected void onGameEnded() {
        notifyAll(EventType.GAME_ENDED);
    }

    @Override
    protected void onRoundStarted() {
        notifyAll(EventType.ROUND_STARTED);
    }

    @Override
    protected void onRoundEnded() {
        notifyAll(EventType.ROUND_ENDED);
    }

    @Override
    protected void onTurnStarted(Identifiable playerId, String strategy) {
        notify(playerId,EventType.TURN_STARTED);
        notifyAll(EventType.TURN_STARTED);
    }

    @Override
    protected void onTurnEnded(Identifiable playerId, String strategy) {
        notify(playerId,EventType.TURN_ENDED);
        notifyAll(EventType.TURN_ENDED);
    }
}
