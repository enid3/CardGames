/**
 * This package contains wide-used abstractions.
 *
 */
package com.github.enid3.cardgames.game.lib.common;


import com.github.enid3.cardgames.game.lib.common.impl.LongIdentifiable;

public abstract class Identifiable {
    // TODO fix: Identifiable equals logic should be comparable to any subclass
    public abstract boolean equals(Object obj);
    public abstract int hashCode();

    public static Identifiable of(long l) {
        return LongIdentifiable.of(l);
    }
}
