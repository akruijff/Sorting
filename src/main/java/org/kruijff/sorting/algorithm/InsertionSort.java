package org.kruijff.sorting.algorithm;

import java.util.*;

public record InsertionSort<T>(Comparator<T> comparator) implements SortAlgorithm<T> {
    @Override
    public String name() {
        return "InsertionSort";
    }

    @Override
    public void sort(T[] arr) {
        for (int i = 1, n = arr.length; i < n; ++i) {
            T key = arr[i];
            int j = i - 1;

            while (j >= 0 && comparator.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
}
