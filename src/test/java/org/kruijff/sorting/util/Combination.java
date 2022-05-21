package org.kruijff.sorting.util;

import java.util.*;

public class Combination {
    private final int[] data;

    public Combination(int selected) {
        data = new int[1];
        data[0] = selected;
    }

    public Combination(int selected, Combination combination) {
        data = new int[combination.data.length + 1];
        data[0] = selected;
        for (int i = 0, n = combination.data.length; i < n; ++i)
            data[i + 1] = combination.data[i];
    }

    public Integer[] get() {
        Integer[] copy = new Integer[data.length];
        for (int i = 0, n = copy.length; i < n; ++i)
            copy[i] = data[i];
        return copy;
    }

    @Override
    public String toString() {
        return Arrays.stream(data)
                .mapToObj(String::valueOf)
                .reduce((l, r) -> l + ", " + r)
                .orElse("");
    }
}
