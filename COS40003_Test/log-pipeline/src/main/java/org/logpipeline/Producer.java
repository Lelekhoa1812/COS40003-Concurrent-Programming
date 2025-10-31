package org.logpipeline;

import java.util.Random;

/* Generates random log entries for a fixed duration, then emits a single END marker */
public class Producer implements Runnable {
    private final BoundedBuffer<LogEntry> out;
    private final int id;

    public Producer(BoundedBuffer<LogEntry> out, int id) {
        this.out = out;
        this.id = id;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        int n = 0;
        Random rng = new Random();

        try {
            while (System.currentTimeMillis() - start < 5000) { // 5 seconds
                n++;
                int r = rng.nextInt(100); // 0..99
                Category cat = (r < 60) ? Category.INFO : (r < 90) ? Category.WARNING : Category.ERROR;
                long ts = System.currentTimeMillis();
                out.put(new LogEntry(ts, cat, "Producer-" + id + " message " + n + "."));
                Thread.sleep(200); // every 200 ms
            }
            out.put(LogEntry.END); // signal completion
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
