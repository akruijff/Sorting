package org.kruijff.sorting;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.kruijff.sorting.algorithm.*;
import org.kruijff.sorting.util.*;

import java.util.*;
import java.util.stream.*;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractSortTest {
    private SortAlgorithm<Integer> algorithm;

    public void setup(SortAlgorithm<Integer> algorithm) {
        this.algorithm = algorithm;
    }

    @AfterEach
    public void teardown() {
        this.algorithm = null;
    }

    @AfterAll
    public static void configuration() {
        RunTest.set(null);
    }

    @Test
    @MyAnnotation (number = 1)
    public void test1() {
        Integer[] arr = {1};
        Integer[] expected = {1};
        Integer[] actual = Arrays.copyOf(arr, arr.length);
        algorithm.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource
    @MyAnnotation (number = 2)
    public void test2(Integer[] arr) {
        test(arr, 2);
    }

    @ParameterizedTest
    @MethodSource
    @MyAnnotation (number = 3)
    public void test3(Integer[] arr) {
        test(arr, 3);
    }

    @ParameterizedTest
    @MethodSource
    @MyAnnotation (number = 4)
    public void test4(Integer[] arr) {
        test(arr, 4);
    }

    @ParameterizedTest
    @MethodSource
    @MyAnnotation (number = 5)
    public void test5(Integer[] arr) {
        test(arr, 5);
    }

    @ParameterizedTest
    @MethodSource
    @MyAnnotation (number = 6)
    public void test6(Integer[] arr) {
        test(arr, 6);
    }

    @ParameterizedTest
    @MethodSource
    @MyAnnotation (number = 7)
    public void test7(Integer[] arr) {
        test(arr, 7);
    }

    @ParameterizedTest
    @MethodSource
    @MyAnnotation (number = 8)
    public void test8(Integer[] arr) {
        test(arr, 8);
    }

    private void test(Integer[] arr, int n) {
        Integer[] expected = create(n);
        Integer[] actual = Arrays.copyOf(arr, arr.length);
        algorithm.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource
    @MyAnnotation (number = 5)
    public void testMultiple(Integer[] arr) {
        Integer[] expected = {1, 1, 2, 2, 3};
        Integer[] actual = Arrays.copyOf(arr, arr.length);
        algorithm.sort(actual);
        assertArrayEquals(expected, actual);
    }

    private Integer[] create(int n) {
        return IntStream.range(1, 1 + n).boxed().toArray(Integer[]::new);
    }

    private static Stream<Arguments> test2() {
        return new Generator().generateArray(2);
    }

    private static Stream<Arguments> test3() {
        return new Generator().generateArray(3);
    }

    private static Stream<Arguments> test4() {
        return new Generator().generateArray(4);
    }

    private static Stream<Arguments> test5() {
        return new Generator().generateArray(5);
    }

    private static Stream<Arguments> test6() {
        return new Generator().generateArray(6);
    }

    private static Stream<Arguments> test7() {
        return new Generator().generateArray(7);
    }

    private static Stream<Arguments> test8() {
        return new Generator().generateArray(8);
    }

    private static Stream<Arguments> testMultiple() {
        return new Generator().generateArray(new int[]{1, 1, 2, 2, 3});
    }
}
