package com.fongfox;

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
}
