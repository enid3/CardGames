package com.github.enid3.cardgames.game.lib.server.handler.impl;

import com.github.enid3.cardgames.game.lib.engine.GeneralGameEngine;
import com.github.enid3.cardgames.game.lib.common.Identifiable;
import com.github.enid3.cardgames.game.lib.engine.state.GameState;
import com.github.enid3.cardgames.game.lib.server.handler.GameEngineHandler;
import com.github.enid3.cardgames.game.lib.server.handler.entity.Message;
import lombok.RequiredArgsConstructor;

import static com.github.enid3.cardgames.game.lib.server.handler.util.LockHelper.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * {@link AbstractGameEngineHandler} responsible for {@link GeneralGameEngine}
 * lifecycle handling. It uses
 *
 */
@RequiredArgsConstructor
public abstract class AbstractGameEngineHandler implements GameEngineHandler {
    protected final static int TIMEOUT = 10000;
    protected final GeneralGameEngine gameEngine;
    protected ReadWriteLock gameEngineLock = new ReentrantReadWriteLock();
    protected Lock readGELock = gameEngineLock.readLock();
    protected Lock writeGELock = gameEngineLock.writeLock();

    protected final BlockingQueue<Message> messages;


    @Override
    public void run() {
        onGameStarted();

        while (wrap(readGELock, gameEngine::anyRoundsLeft)) {
            wrap(writeGELock, gameEngine::prepareRound);
            onRoundStarted();

            while (wrap(readGELock, gameEngine::anyTurnsLeft)) {
                try {
                    Message message = messages.take();
                    Identifiable playerId = message.getId();
                    String strategy = message.getStrategy();

                    onTurnStarted(playerId, strategy);
                    try {
                        writeGELock.lock();
                        gameEngine.handleTurn(playerId, strategy);
                    }
                    finally {
                        writeGELock.unlock();
                    }
                    onTurnEnded(playerId, strategy);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            wrap(writeGELock, gameEngine::finishRound);
            onRoundEnded();
        }
        onGameEnded();
    }

    protected abstract void onGameStarted();
    protected abstract void onGameEnded();
    protected abstract void onRoundStarted();
    protected abstract void onRoundEnded();
    protected abstract void onTurnStarted(Identifiable playerId, String strategy);
    protected abstract void onTurnEnded(Identifiable playerId, String strategy);

    @Override
    public void handlePlayerStrategy(Identifiable playerId, String strategy) {
        try {
            messages.put(new Message(playerId, strategy));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameState getGameState() {
        return wrap(readGELock, gameEngine::getState);
    }
}

