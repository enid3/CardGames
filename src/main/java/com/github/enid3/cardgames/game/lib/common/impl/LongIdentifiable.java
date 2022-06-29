package com.github.enid3.cardgames.game.lib.common.impl;

import com.github.enid3.cardgames.game.lib.common.Identifiable;

import lombok.Getter;

import java.util.Objects;

@Getter
public class LongIdentifiable extends Identifiable {
    private final long id;

    protected LongIdentifiable(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LongIdentifiable)) return false;
        LongIdentifiable that = (LongIdentifiable) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static LongIdentifiable of(long l) {
        return new LongIdentifiable(l);
    }
}
