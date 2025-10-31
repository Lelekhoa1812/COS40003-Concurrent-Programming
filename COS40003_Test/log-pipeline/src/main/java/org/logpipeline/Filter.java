package org.logpipeline;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Pipeline stage that drops INFO logs and forwards WARNING/ERROR logs downstream.
 * Counts by category for reporting. Waits for a single END marker after all
 * producers have signaled completion.
 */
public class Filter implements Runnable {
    private final BoundedBuffer<LogEntry> in;
    private final BoundedBuffer<LogEntry> out;
    private final int producerCount;

    private final AtomicInteger infoCount = new AtomicInteger();
    private final AtomicInteger warningCount = new AtomicInteger();
    private final AtomicInteger errorCount = new AtomicInteger();

    public Filter(BoundedBuffer<LogEntry> in, BoundedBuffer<LogEntry> out, int producerCount) {
        this.in = in;
        this.out = out;
        this.producerCount = producerCount;
    }

    @Override
    public void run() {
        int endsSeen = 0;
        try {
            while (true) {
                LogEntry e = in.take();
                if (e == LogEntry.END) {
                    if (++endsSeen == producerCount) { // all producers finished
                        out.put(LogEntry.END); // forward a single terminator downstream
                        break;
                    }
                    continue;
                }
                if (e.category() == Category.INFO) {
                    infoCount.incrementAndGet(); // discard INFO entries
                } else {
                    if (e.category() == Category.WARNING) warningCount.incrementAndGet();
                    else errorCount.incrementAndGet();
                    out.put(e);
                }
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    public int getInfoCount()    { return infoCount.get(); }
    public int getWarningCount() { return warningCount.get(); }
    public int getErrorCount()   { return errorCount.get(); }
}
