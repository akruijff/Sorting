package org.kruijff.sorting.algorithm;

import java.util.*;

import static org.kruijff.sorting.algorithm.util.SwapUtil.*;
import static org.kruijff.sorting.algorithm.util.ThreadUtil.*;

public record QuickSort<T>(Comparator<T> objectComparator,
                           int maxThreadLevel) implements SortAlgorithm<T>, PartialSortAlgorithm<T> {
    public QuickSort(Comparator<T> objectComparator) {
        this(objectComparator, 8);
    }

    @Override
    public String name() {
        return "QuickSort";
    }

    @Override
    public void sort(T[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    @Override
    public void sort(T[] arr, int low, int high) {
        sort(arr, low, high, 0);
    }

    private void sort(T[] arr, int low, int high, int level) {
        if (low >= high)
            return;

        int p = partition(arr, low, high);
        if (level >= maxThreadLevel) {
            sort(arr, low, p - 1, level);
            sort(arr, p + 1, high, level);
        } else {
            Thread t = async(() -> sort(arr, low, p - 1, level + 1));
            sort(arr, p + 1, high, level + 1);
            join(t);
        }
    }

    private int partition(T[] arr, int low, int high) {
        T pivot = swapPivotOut(arr, high, (low + high) / 2);
        int middle = divideRangeByPivot(arr, low, high - 1, pivot);
        return swapPivotIn(arr, high, middle);
    }

    private T swapPivotOut(T[] arr, int high, int p) {
        T pivot = arr[p];
        swap(arr, p, high);
        return pivot;
    }

    private int divideRangeByPivot(T[] arr, int l, int r, T pivot) {
        while (l < r) {
            while (objectComparator.compare(arr[l], pivot) <= 0 && l < r)
                l++;
            while (objectComparator.compare(arr[r], pivot) >= 0 && l < r)
                r--;
            swap(arr, l, r);
        }
        return l;
    }

    private int swapPivotIn(T[] arr, int high, int p) {
        if (objectComparator.compare(arr[p], arr[high]) <= 0)
            return high;
        swap(arr, p, high);
        return p;
    }
}
