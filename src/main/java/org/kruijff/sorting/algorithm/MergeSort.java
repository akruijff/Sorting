package org.kruijff.sorting.algorithm;

import java.util.*;

import static org.kruijff.sorting.algorithm.util.ThreadUtil.*;

public record MergeSort<T>(Comparator<T> comparator,
                           int maxThreadLevel) implements SortAlgorithm<T>, PartialSortAlgorithm<T> {
    public MergeSort(Comparator<T> objectComparator) {
        this(objectComparator, 4);
    }

    @Override
    public String name() {
        return "MergeSort";
    }

    @Override
    public void sort(T[] arr) {
        sort(arr, 0, arr.length);
    }

    @Override
    public void sort(T[] arr, int low, int high) {
        T[] work = Arrays.copyOf(arr, arr.length);
        sort(arr, work, low, high - 1, 0);
    }

    private void sort(T[] arr, T[] work, int low, int high, int level) {
        if (high - low < 1)
            return;
        int p = (low + high) / 2;
        if (level >= maxThreadLevel) {
            sort(work, arr, low, p, level);
            sort(work, arr, p + 1, high, level);
        } else {
            Thread t = async(() -> sort(work, arr, low, p, level + 1));
            sort(work, arr, p + 1, high, level + 1);
            join(t);
        }
        merge(work, arr, low, p + 1, high);
    }

    private void merge(T[] arr, T[] work, int low, int p, int high) {
        int i = low, j = p;
        for (int k = low; k <= high; k++)
            work[k] = i < p && (j > high || comparator.compare(arr[i], arr[j]) <= 0)
                    ? arr[i++] : arr[j++];
    }
}
