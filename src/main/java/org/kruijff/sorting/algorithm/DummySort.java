package org.kruijff.sorting.algorithm;

public class DummySort<T> implements SortAlgorithm<T> {
    @Override
    public String name() {
        return "DummySort";
    }

    @Override
    public void sort(T[] arr) {
    }
}
