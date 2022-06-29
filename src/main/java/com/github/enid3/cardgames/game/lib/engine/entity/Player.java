package com.github.enid3.cardgames.game.lib.engine.entity;

import com.github.enid3.cardgames.game.lib.common.Identifiable;
import com.github.enid3.cardgames.game.lib.common.impl.LongIdentifiable;
import lombok.*;

import java.util.Objects;

@Getter
@Setter

public class Player extends LongIdentifiable {
    private long tokens;

    public Player(long id, long tokens) {
        super(id);
        this.tokens = tokens;
    }
    public Player(Player p) {
        super(p.getId());
        this.tokens = p.getTokens();
    }

    public long looseTokens(long val) {
        this.setTokens(getTokens() - val);
        return getTokens();
    }
    public long winTokens(long val) {
        this.setTokens(getTokens() + val);
        return getTokens();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + getId() + ", " +
                "tokens=" + tokens +
                '}';
    }
}
