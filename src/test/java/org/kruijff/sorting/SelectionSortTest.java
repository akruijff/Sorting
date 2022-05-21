package org.kruijff.sorting;

import org.junit.jupiter.api.*;
import org.kruijff.sorting.algorithm.*;
import org.kruijff.sorting.util.*;

import java.util.*;

public class SelectionSortTest extends AbstractSortTest {
    @BeforeAll
    public static void configuration() {
        RunTest.set(true, true, true, true, true, true, true, true);
    }

    @BeforeEach
    public void setup() {
        setup(new SelectionSort<>(Comparator.comparingInt(i -> i)));
    }
}
