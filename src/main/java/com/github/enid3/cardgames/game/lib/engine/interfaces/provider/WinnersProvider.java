package com.github.enid3.cardgames.game.lib.engine.interfaces.provider;

import com.github.enid3.cardgames.game.lib.common.Identifiable;
import com.github.enid3.cardgames.game.lib.engine.entity.Player;

import java.util.List;

public interface WinnersProvider {
    Iterable<Identifiable> getWinners();
}
