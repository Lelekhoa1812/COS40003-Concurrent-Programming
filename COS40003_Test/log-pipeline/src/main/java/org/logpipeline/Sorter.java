package org.logpipeline;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/* Batches incoming log entries for up to ~1s, sorts them by timestamp, prints them in order, and repeats until a single END marker is received */
public class Sorter implements Runnable {
    private final BoundedBuffer<LogEntry> in;
    private final DateTimeFormatter fmt =
            DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSS")
                    .withZone(ZoneId.systemDefault());

    public Sorter(BoundedBuffer<LogEntry> in) {
        this.in = in;
    }

    @Override
    public void run() {
        List<LogEntry> batch = new ArrayList<>();
        boolean done = false;

        try {
            while (!done) {
                long nextFlush = System.currentTimeMillis() + 1000L;

                while (true) {
                    long remain = nextFlush - System.currentTimeMillis();
                    if (remain <= 0) break;

                    LogEntry e = in.take(remain); // timed wait to avoid busy-waiting
                    if (e == null) break;         // timeout -> flush
                    if (e == LogEntry.END) {
                        done = true;
                        break;
                    }
                    batch.add(e);
                }

                flushBatch(batch);
            }

            // final flush in case END arrived quickly with residual logs in batch
            flushBatch(batch);

        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    /* Sorts the batch by time and prints, then clears the list */
    private void flushBatch(List<LogEntry> batch) {
        if (batch.isEmpty()) return;
        batch.sort(Comparator.comparingLong(LogEntry::timestampMs));
        System.out.println("-----------------------------");
        for (LogEntry e : batch) {
            String ts = fmt.format(Instant.ofEpochMilli(e.timestampMs()));
            System.out.printf("[%s][%s] %s%n", ts, e.category(), e.message());
        }
        batch.clear();
    }
}
