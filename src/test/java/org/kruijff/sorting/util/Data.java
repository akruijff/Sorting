package org.kruijff.sorting.util;

import java.util.*;

public class Data {
    private final int[] data;

    public Data(int... data) {
        this.data = data;
    }

    public int size() {
        return data.length;
    }

    public int get(int index) {
        return data[index];
    }

    public Data remove(int index) {
        int[] remainder = new int[data.length - 1];
        System.arraycopy(data, 0, remainder, 0, index);
        if (index < remainder.length)
            System.arraycopy(data, index + 1, remainder, index, data.length - index - 1);
        return new Data(remainder);
    }

    @Override
    public String toString() {
        return data.length + " = [" + Arrays.stream(data)
                .mapToObj(String::valueOf)
                .reduce((l, r) -> l + ", " + r)
                .orElse("") + "]";
    }
}
