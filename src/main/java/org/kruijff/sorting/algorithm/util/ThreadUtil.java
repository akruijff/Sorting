package org.kruijff.sorting.algorithm.util;

public class ThreadUtil {
    public static Thread async(Runnable runnable) {
        Thread t = new Thread(runnable);
        t.start();
        return t;
    }

    public static boolean join(Thread t) {
        return join(t, false);
    }

    public static boolean join(Thread t, boolean throwException) {
        try {
            t.join();
            return true;
        } catch (InterruptedException e) {
            if (throwException)
                throw new UnsupportedOperationException(e);
        }
        return false;
    }
}
