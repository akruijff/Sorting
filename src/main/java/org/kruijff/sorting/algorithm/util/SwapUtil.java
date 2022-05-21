package org.kruijff.sorting.algorithm.util;

public class SwapUtil {
    public static <T> void swap(T[] arr, int lower, int higher) {
        T tmp = arr[lower];
        arr[lower] = arr[higher];
        arr[higher] = tmp;
    }
}
