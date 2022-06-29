package com.github.enid3.cardgames.webapp.service.brocker;

import com.github.enid3.cardgames.game.lib.common.Identifiable;

public interface IdentifiableBrocker {
    long id(Identifiable id);
    Identifiable id(long id);
    Identifiable id(long id, long modifier);
}
