package org.kruijff.sorting;

import org.junit.jupiter.api.*;
import org.kruijff.sorting.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GeneratorTest {
    @Test
    public void test1() {
        Integer[][] actual = new Generator()
                .generateArray(1)
                .map(arg -> (Integer[]) arg.get()[0])
                .toArray(Integer[][]::new);
        Integer[][] expected = {{1}};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void test2() {
        Integer[][] actual = new Generator()
                .generateArray(2)
                .map(arg -> (Integer[]) arg.get()[0])
                .toArray(Integer[][]::new);
        Integer[][] expected = {
                {1, 2},
                {2, 1}};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void test3() {
        Integer[][] actual = new Generator()
                .generateArray(3)
                .map(arg -> (Integer[]) arg.get()[0])
                .toArray(Integer[][]::new);
        Integer[][] expected = {
                {1, 2, 3},
                {1, 3, 2},
                {2, 1, 3},
                {2, 3, 1},
                {3, 1, 2},
                {3, 2, 1}};
        assertArrayEquals(expected, actual);
    }
}
