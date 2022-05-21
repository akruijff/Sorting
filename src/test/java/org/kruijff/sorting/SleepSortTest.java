package org.kruijff.sorting;

import org.junit.jupiter.api.*;
import org.kruijff.sorting.algorithm.*;
import org.kruijff.sorting.util.*;

public class SleepSortTest extends AbstractSortTest {
    @BeforeAll
    public static void configuration() {
        RunTest.set(true, true, true);
    }

    @BeforeEach
    public void setup() {
        setup(new SleepSort<>());
    }
}
