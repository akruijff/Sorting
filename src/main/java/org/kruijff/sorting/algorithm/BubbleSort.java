package org.kruijff.sorting.algorithm;

import java.util.*;

import static org.kruijff.sorting.algorithm.util.SwapUtil.swap;

public record BubbleSort<T>(Comparator<T> comparator) implements SortAlgorithm<T> {
    @Override
    public String name() {
        return "BubbleSort";
    }

    @Override
    public void sort(T[] arr) {
        int n = arr.length;
        while (n >= 2) {
            int max = 0;
            for (int i = 1; i < n; ++i)
                if (comparator.compare(arr[i - 1], arr[i]) > 0) {
                    swap(arr, i - 1, i);
                    max = i;
                }
            n = max;
        }
    }
}
