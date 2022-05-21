package org.kruijff.sorting;

import org.kruijff.sorting.algorithm.*;

import java.util.*;
import java.util.function.*;

public class Main {

    public static final int SIZE = 100_000_000;
    public static final int BOUND = 1_000_000_000;

    public static void main(String[] args) {
        try {
            List<Integer> unsortedList = createRandomList(SIZE, 0, BOUND);
            Integer[] unsortedArr = unsortedList.toArray(new Integer[0]);

            System.out.println(toString(unsortedArr, "Input: "));
            Comparator<Integer> comparator = Comparator.comparingInt(i -> i);
            sort(unsortedArr, new HybridSort<>(comparator));
            sort(unsortedArr, new MergeSort<>(comparator));
            sort(unsortedArr, new QuickSort<>(comparator));
            sort(unsortedArr, new HybridSort<>(comparator, HybridSort.PAGE_SIZE,0));
            sort(unsortedArr, new MergeSort<>(comparator, 0));
            sort(unsortedArr, new QuickSort<>(comparator, 0));
//            sort(unsortedArr, new HeapSort<>(comparator));
//            sort(unsortedArr, new InsertionSort<>(comparator));
//            sort(unsortedArr, new SelectionSort<>(comparator));
//            sort(unsortedArr, new BubbleSort<>(comparator));
//            algorithm(unsortedArr, new SleepSort<>());
//            algorithm(unsortedArr, new BogoSort<>(comparator));
//            System.out.println("Winner: " + test(
//                    unsortedArr, 1, 8, i -> new HybridSort<>(comparator, HybridSort.PAGE_SIZE, i)));
            System.out.println("Done");
        } catch (Exception e) {
            System.err.println("An unknown error occurred.");
        }
    }

    private static long test(Integer[] unsortedArr, int min, int max, Function<Integer, SortAlgorithm<Integer>> f) {
        long fastest = Long.MAX_VALUE;
        long index = 0;
        for (int i = min; i < max; ++i) {
            long t = sort(unsortedArr, f.apply(i));
            if (t < fastest) {
                index = i;
                fastest = t;
            }
        }
        return index;
    }

    private static long sort(Integer[] arr, SortAlgorithm<Integer> algorithm) {
        Integer[] sortedArr = Arrays.copyOf(arr, arr.length);
        long start = System.currentTimeMillis();
        algorithm.sort(sortedArr);
        long time = System.currentTimeMillis() - start;
        System.out.println(algorithm.name() + " time: " + time + " ms" + toString(arr, " output: "));
        return time;
    }

    private static List<Integer> createRandomList(int size, int origin, int bound) {
        return new Random()
                .ints(size, origin, bound)
                .boxed()
                .toList();
    }

    private static String toString(Integer[] arr, String prefix) {
        return arr.length <= SIZE && BOUND <= 10 ? prefix + toString(List.of(arr)) : "";
    }

    private static String toString(List<Integer> list) {
        return list.stream()
                .map(i -> Integer.toString(i))
                .reduce((l, r) -> l + ", " + r)
                .orElse("");
    }
}
