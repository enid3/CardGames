package com.github.enid3.cardgames.game.lib.common.impl;

import com.github.enid3.cardgames.game.lib.common.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
public class LongLongIdentifiable extends LongIdentifiable {
    protected final long modifier;

    protected LongLongIdentifiable(long id, long modifier) {
        super(id);
        this.modifier = modifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongLongIdentifiable that = (LongLongIdentifiable) o;
        return id == that.id && modifier == that.modifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modifier);
    }
    public static LongLongIdentifiable of(long id, long modifier) {
        return new LongLongIdentifiable(id, modifier);
    }
}
