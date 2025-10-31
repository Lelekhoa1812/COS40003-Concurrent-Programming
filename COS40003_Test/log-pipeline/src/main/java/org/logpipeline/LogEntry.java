package org.logpipeline;

/* Immutable log record used across pipeline stages. The special constant {@link #END} acts as a sentinel to signal completion */
public record LogEntry(long timestampMs, Category category, String message) {
    public static final LogEntry END = new LogEntry(-1L, Category.INFO, "END");
}