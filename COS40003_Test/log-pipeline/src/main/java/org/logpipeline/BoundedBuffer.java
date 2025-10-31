package org.logpipeline;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class BoundedBuffer<T> {
    private final Object[] items;
    private int head = 0;   // index of next element to take
    private int tail = 0;   // index to place next put element
    private int count = 0;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull  = lock.newCondition();

    /* Create a buffer with the given capacity (> 0) */
    public BoundedBuffer(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");
        this.items = new Object[capacity];
    }

    /* Inserts an element, blocking if the buffer is full until space becomes available */
    public void put(T x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) notFull.await();
            items[tail] = x;
            tail = (tail + 1) % items.length;
            count++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /* Removes and returns an element, blocking if the buffer is empty until one arrives */
    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) notEmpty.await();
            @SuppressWarnings("unchecked")
            T x = (T) items[head];
            items[head] = null;
            head = (head + 1) % items.length;
            count--;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }

    /** Timed take: returns null on timeout (no busy-wait). */
    public T take(long timeoutMs) throws InterruptedException {
        long nanos = TimeUnit.MILLISECONDS.toNanos(timeoutMs);
        lock.lock();
        try {
            while (count == 0) {
                if (nanos <= 0L) return null;
                nanos = notEmpty.awaitNanos(nanos);
            }
            @SuppressWarnings("unchecked")
            T x = (T) items[head];
            items[head] = null;
            head = (head + 1) % items.length;
            count--;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
