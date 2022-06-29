package com.github.enid3.cardgames.webapp.service.brocker;

import com.github.enid3.cardgames.game.lib.common.Identifiable;
import com.github.enid3.cardgames.game.lib.engine.entity.Player;

import javax.validation.constraints.NotNull;

public interface PlayerBrocker {
    @NotNull Player reserve(long userid, long tokens);
    @NotNull Player cast(long userid);

}
