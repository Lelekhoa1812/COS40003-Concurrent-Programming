package org.logpipeline;

/* Wires the pipeline: Producer(s) -> Filter -> Sorter, runs threads, and prints a summary when complete */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int PRODUCER_COUNT = 2;

        BoundedBuffer<LogEntry> raw = new BoundedBuffer<LogEntry>(64);
        BoundedBuffer<LogEntry> filtered = new BoundedBuffer<LogEntry>(64);

        Thread p1 = new Thread(new Producer(raw, 1), "producer-1");
        Thread p2 = new Thread(new Producer(raw, 2), "producer-2");

        Filter filter = new Filter(raw, filtered, PRODUCER_COUNT);
        Thread f = new Thread(filter, "filter");

        Sorter sorter = new Sorter(filtered);
        Thread s = new Thread(sorter, "sorter");

        p1.start(); p2.start(); f.start(); s.start();
        p1.join(); p2.join(); f.join(); s.join();

        System.out.printf(
                "Processed INFO = %d, WARNING = %d, ERROR = %d%n",
                filter.getInfoCount(), filter.getWarningCount(), filter.getErrorCount()
        );
        System.out.println("Done.");
    }
}