package org.kruijff.sorting.algorithm;

import java.util.*;

import static org.kruijff.sorting.algorithm.util.ThreadUtil.*;

public record HybridSort<T>(Comparator<T> comparator,
                            PartialSortAlgorithm<T> algorithm,
                            int pageSize,
                            int maxThreadLevel) implements SortAlgorithm<T> {

    public static final int PAGE_SIZE = 1024 * 1024;
    public static final int MAX_THREAD_LEVEL = 4;

    public HybridSort(Comparator<T> comparator) {
        this(comparator, PAGE_SIZE, MAX_THREAD_LEVEL);
    }

    public HybridSort(Comparator<T> comparator, PartialSortAlgorithm<T> algorithm) {
        this(comparator, algorithm, PAGE_SIZE, MAX_THREAD_LEVEL);
    }

    public HybridSort(Comparator<T> comparator, int pageSize, int maxThreadLevel) {
        this(comparator, new QuickSort<>(comparator, 0), pageSize, maxThreadLevel);
    }

    @Override
    public String name() {
        return "HybridSort::" + algorithm.name();
    }

    @Override
    public void sort(T[] arr) {
        T[] work = Arrays.copyOf(arr, arr.length);
        sort(arr, work, 0, blocks(arr) - 1, 0);
    }

    private int blocks(T[] arr) {
        int blocks = pageSize;
        while (blocks < arr.length)
            blocks <<= 2;
        return blocks;
    }

    private void sort(T[] arr, T[] work, int low, int high, int level) {
        if (high - low < pageSize) {
            algorithm.sort(arr, low, Math.min(high, arr.length - 1));
            return;
        }
        int p = (low + high) / 2;
        if (level >= maxThreadLevel) {
            sort(work, arr, low, p, level);
            sort(work, arr, p + 1, high, level);
        } else {
            Thread t = async(() -> sort(work, arr, low, p, level + 1));
            sort(work, arr, p + 1, high, level + 1);
            join(t);
        }
        merge(work, arr, low, p + 1, Math.min(high, arr.length - 1));
    }

    private void merge(T[] arr, T[] work, int low, int p, int high) {
        int i = low, j = p;
        for (int k = low; k <= high; k++)
            work[k] = i < p && (j > high || comparator.compare(arr[i], arr[j]) <= 0)
                    ? arr[i++] : arr[j++];
    }
}
