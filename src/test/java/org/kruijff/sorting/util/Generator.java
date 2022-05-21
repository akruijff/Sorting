package org.kruijff.sorting.util;

import org.junit.jupiter.params.provider.*;

import java.util.*;
import java.util.stream.*;

public class Generator {
    public Stream<Arguments> generateList(int n) {
        Arguments[] arr = combinations(n).stream()
                .map(combination -> Arguments.of(Arrays.stream(combination.get()).toList()))
                .toArray(Arguments[]::new);
        return Stream.of(arr);
    }

    public Stream<Arguments> generateArray(int n) {
        Arguments[] arr = combinations(n).stream()
                .map(combination -> Arguments.of((Object) combination.get()))
                .toArray(Arguments[]::new);
        return Stream.of(arr);
    }

    public Stream<Arguments> generateArray(int[] data) {
        Arguments[] arr =combinations(new Data(data)).stream()
                .map(combination -> Arguments.of((Object) combination.get()))
                .toArray(Arguments[]::new);
        return Stream.of(arr);
    }

    private CombinationResult combinations(int n) {
        Data data = new Data(IntStream.range(1, 1 + n).toArray());
        return combinations(data);
    }

    private CombinationResult combinations(Data data) {
        CombinationResult result = new CombinationResult();
        for (int i = 0, n = data.size(); i < n; ++i) {
            int selected = data.get(i);
            Data remainder = data.remove(i);
            if (remainder.size() == 0)
                result.add(new Combination(selected));
            else
                for (Combination combination : combinations(remainder))
                    result.add(new Combination(selected, combination));
        }
        return result;
    }
}
