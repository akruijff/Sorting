package org.kruijff.sorting.util;

public class RunTest {
    private static boolean[] runTest;

    public static boolean get(int index) {
        return runTest == null || index > 0 && index <= runTest.length && runTest[index - 1];
    }

    public static void set(boolean... flags) {
        runTest = flags;
    }
}
