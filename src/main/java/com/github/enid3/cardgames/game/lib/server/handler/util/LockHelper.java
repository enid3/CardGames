package com.github.enid3.cardgames.game.lib.server.handler.util;

import java.util.concurrent.locks.Lock;
import java.util.function.Supplier;

public final class LockHelper {
    private LockHelper(){}
    public static <T> T wrap(Lock lock, Supplier<T> s) {
        try {
            lock.lock();
            return s.get();
        }
        finally {
            lock.unlock();
        }
    }
    public static void wrap(Lock lock, Runnable r) {
        try {
            lock.lock();
            r.run();
        }
        finally {
            lock.unlock();
        }
    }

}
