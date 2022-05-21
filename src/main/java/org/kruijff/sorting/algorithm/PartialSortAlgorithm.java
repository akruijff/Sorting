package org.kruijff.sorting.algorithm;

public interface PartialSortAlgorithm<T> {
    String name();

    void sort(T[] arr, int low, int high);
}
