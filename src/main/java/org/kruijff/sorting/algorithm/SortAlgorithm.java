package org.kruijff.sorting.algorithm;

public interface SortAlgorithm<T> {
    String name();

    void sort(T[] arr);
}
