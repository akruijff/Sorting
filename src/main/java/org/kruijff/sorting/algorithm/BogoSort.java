package org.kruijff.sorting.algorithm;

import java.util.*;

import static org.kruijff.sorting.algorithm.util.SwapUtil.swap;

public record BogoSort<T>(Comparator<T> comparator) implements SortAlgorithm<T>{
    @Override
    public String name() {
        return "BogoSort";
    }

    @Override
    public void sort(T[] arr) {
        while(!isSorted(arr))
            shuffle(arr);
    }

    private boolean isSorted(T[] arr) {
        for (int i = 0, n = arr.length - 1; i < n; ++i)
            if (comparator.compare(arr[i], arr[i + 1]) >= 0)
                return false;
        return true;
    }

    private void shuffle(T[] arr) {
        Random random = new Random();
        for (int i = 0, n = arr.length; i < n; ++i)
            swap(arr, i, random.nextInt(n));
    }
}
