package com.github.enid3.cardgames.webapp.service.impl;

import com.github.enid3.cardgames.game.lib.common.Identifiable;
import com.github.enid3.cardgames.game.lib.common.impl.LongIdentifiable;
import com.github.enid3.cardgames.game.lib.engine.entity.Player;
import com.github.enid3.cardgames.webapp.service.brocker.IdentifiableBrocker;
import com.github.enid3.cardgames.webapp.service.brocker.PlayerBrocker;
import org.springframework.stereotype.Service;

@Service
public class BrockerImpl implements PlayerBrocker, IdentifiableBrocker {
    @Override
    public Player reserve(long userid, long tokens) {
        // TODO impliment: tokens reserving logic
        return new Player(userid, tokens);
    }

    @Override
    public Player cast(long userid) {
        return new Player(userid, 0);
    }

    @Override
    public long id(Identifiable id) {
        if(id instanceof LongIdentifiable)  {
            LongIdentifiable li = (LongIdentifiable) id;
            return li.getId();
        }
        throw new IllegalArgumentException("This version of identifiable not supported");
    }

    @Override
    public Identifiable id(long id) {
        return LongIdentifiable.of(id);
    }

    @Override
    public Identifiable id(long id, long modifier) {
        return LongIdentifiable.of(id);
    }
}
