package org.kruijff.sorting.algorithm;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class SleepSort<T extends Number> implements SortAlgorithm<T> {
    public final int sleepFactor;

    public SleepSort() {
        this(200);
    }

    public SleepSort(int sleepFactor) {
        this.sleepFactor = sleepFactor;
    }

    @Override
    public String name() {
        return "SleepSort";
    }

    @Override
    public void sort(T[] arr) {
        BlockingQueue<T> queue = process(Arrays.stream(arr), arr.length);
        T[] work = queue.toArray(n -> Arrays.copyOf(arr, 0));
        System.arraycopy(work, 0, arr, 0, arr.length);
    }

    private BlockingQueue<T> process(Stream<T> stream, int n) {
        BlockingQueue<T> queue = new ArrayBlockingQueue<>(n);
        Thread[] threads = stream.map(t -> new Thread(() -> process(queue, t))).toArray(Thread[]::new);
        Arrays.stream(threads).forEach(Thread::start);
        Arrays.stream(threads).forEach(this::join);
        return queue;
    }

    private void process(BlockingQueue<T> queue, T arr) {
        try {
            Thread.sleep(sleepFactor * arr.longValue());
        } catch (InterruptedException e) {
            throw new UnsupportedOperationException(e);
        } finally {
            queue.add(arr);
        }
    }

    private void join(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
