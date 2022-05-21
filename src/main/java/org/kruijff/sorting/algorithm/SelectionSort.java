package org.kruijff.sorting.algorithm;

import java.util.*;

import static org.kruijff.sorting.algorithm.util.SwapUtil.*;

public record SelectionSort<T>(Comparator<T> comparator) implements SortAlgorithm<T> {
    @Override
    public String name() {
        return "SelectionSort";
    }

    @Override
    public void sort(T[] arr) {
        int size = arr.length;
        for (int j = 0; j < size - 1; j++) {
            int min = j;

            for (int i = j + 1; i < size; i++)
                if (comparator.compare(arr[i], arr[min]) < 0)
                    min = i;
            swap(arr, j, min);
        }
    }
}
