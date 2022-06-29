package com.github.enid3.cardgames.game.lib.engine.interfaces;

import com.github.enid3.cardgames.game.lib.engine.entity.Player;

import java.util.Collection;

public interface ReusableGameEngine {
    void joinPlayers(Collection<? extends Player> players);
    void clear();

}
