package org.kruijff.sorting;

import org.junit.jupiter.api.*;
import org.kruijff.sorting.algorithm.*;
import org.kruijff.sorting.util.*;

import java.util.*;

public class HybridSortTest extends AbstractSortTest {
    @BeforeAll
    public static void configuration() {
        RunTest.set(true, true, true, true, true, true, true, true);
    }

    @BeforeEach
    public void setup() {
        Comparator<Integer> comparator = Comparator.comparingInt(i -> i);
        setup(new HybridSort<>(comparator, new QuickSort<>(comparator, 0), 2, 2));
    }
}
