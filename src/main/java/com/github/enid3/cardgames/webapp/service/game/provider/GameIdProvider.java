package com.github.enid3.cardgames.webapp.service.game.provider;

public interface GameIdProvider {
    long getNextGameId(String name);
}
