package com.github.enid3.cardgames.game.lib.engine.interfaces;

public interface RoundBasedGameEngine {
    void prepareRound();
    void finishRound();
    boolean anyRoundsLeft();
}
