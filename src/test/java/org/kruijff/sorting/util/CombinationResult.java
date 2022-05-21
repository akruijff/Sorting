package org.kruijff.sorting.util;

import java.util.*;
import java.util.stream.*;

public class CombinationResult implements Iterable<Combination> {
    private final List<Combination> backend = new ArrayList<>();

    public void add(Combination combination) {
        backend.add(combination);
    }

    @Override
    public Iterator<Combination> iterator() {
        return backend.listIterator();
    }

    public Stream<Combination> stream() {
        return backend.stream();
    }

    @Override
    public String toString() {
        return backend.size() + " = {" + backend.stream()
                .map(Object::toString)
                .reduce((l, r) -> l + "; " + r)
                .orElse("") + "}";
    }
}
