package com.fongfox;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogAnalyzer {
    private final List<LogEntry> logEntries;

    public LogAnalyzer(List<LogEntry> logEntries) {
        this.logEntries = logEntries;
    }

    public Map<LogLevel, Long> countByLevel() {
        return logEntries.stream().collect(Collectors.groupingBy(LogEntry::getLevel, Collectors.counting()));
    }

    public List<LogEntry> findErrors() {
        return logEntries.stream()
                .filter(entry -> entry.getLevel() == LogLevel.FATAL || entry.getLevel() == LogLevel.ERROR)
                .collect(Collectors.toList());
    }

    public TimeRange getTimeRange() {
        LocalDateTime earliest = logEntries.stream()
                .min(Comparator.comparing(LogEntry::getTimestamp))
                .map(LogEntry::getTimestamp)
                .orElseThrow(() -> new IllegalStateException("No log entries available to determine time range."));

        LocalDateTime latest = logEntries.stream()
                .max(Comparator.comparing(LogEntry::getTimestamp))
                .map(LogEntry::getTimestamp)
                .orElse(null);

        return new TimeRange(earliest, latest);
    }

}
