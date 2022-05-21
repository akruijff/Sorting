package org.kruijff.sorting.algorithm;

import java.util.*;

import static org.kruijff.sorting.algorithm.util.SwapUtil.*;

public record HeapSort<T>(Comparator<T> comparator) implements SortAlgorithm<T> {
    @Override
    public String name() {
        return "HeapSort";
    }

    @Override
    public void sort(T[] arr) {
        buildMaxHeap(arr);
        heapSort(arr);
    }

    private void buildMaxHeap(T[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
    }

    private void heapSort(T[] copy) {
        int n = copy.length;
        for (int i = n - 1; i >= 0; i--) {
            swap(copy, i, 0);
            heapify(copy, i, 0);
        }
    }

    void heapify(T arr[], int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && comparator.compare(arr[l] , arr[largest]) > 0)
            largest = l;
        if (r < n && comparator.compare(arr[r] , arr[largest]) > 0)
            largest = r;
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }
}