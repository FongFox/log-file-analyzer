package com.fongfox;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LogAnalyzerTest {
    private List<LogEntry> createSampleLogs() {
        return Arrays.asList(
                new LogEntry(LocalDateTime.of(2026, 1, 20, 12, 0, 0), "thread-1", LogLevel.DEBUG, "AuthService", "Start"),
                new LogEntry(LocalDateTime.of(2026, 1, 21, 13, 30, 0), "thread-2", LogLevel.INFO, "OrderService", "Processing"),
                new LogEntry(LocalDateTime.of(2026, 1, 22, 14, 45, 0), "thread-3", LogLevel.ERROR, "PaymentService", "Failed"),
                new LogEntry(LocalDateTime.of(2026, 1, 23, 15, 55, 0), "thread-4", LogLevel.FATAL, "DBService", "Crash")
        );
    }

    @Test
    void testCountByLevel() {
        List<LogEntry> logs = createSampleLogs();
        LogAnalyzer analyzer = new LogAnalyzer(logs);

        Map<LogLevel, Long> counts = analyzer.countByLevel();

        assertEquals(1L, counts.get(LogLevel.DEBUG));
        assertEquals(1L, counts.get(LogLevel.INFO));
        assertEquals(1L, counts.get(LogLevel.ERROR));
        assertEquals(1L, counts.get(LogLevel.FATAL));

        // Không có WARN nên null
        assertNull(counts.get(LogLevel.WARN));
    }

    @Test
    void testFindErrors() {
        List<LogEntry> logs = createSampleLogs();
        LogAnalyzer analyzer = new LogAnalyzer(logs);

        List<LogEntry> errors = analyzer.findErrors();

        // Đảm bảo chỉ có 2 entry (ERROR + FATAL)
        assertEquals(2, errors.size());
        assertTrue(errors.stream()
                .allMatch(e -> e.getLevel() == LogLevel.ERROR || e.getLevel() == LogLevel.FATAL));
    }

    @Test
    void testGetTimeRange() {
        List<LogEntry> logs = createSampleLogs();
        LogAnalyzer analyzer = new LogAnalyzer(logs);

        TimeRange range = analyzer.getTimeRange();

        assertEquals(LocalDateTime.of(2026, 1, 20, 12, 0, 0), range.getStart());
        assertEquals(LocalDateTime.of(2026, 1, 23, 15, 55, 0), range.getEnd());
    }

    @Test
    void testGetTimeRangeEmpty() {
        LogAnalyzer analyzer = new LogAnalyzer(Collections.emptyList());

        assertThrows(IllegalStateException.class, analyzer::getTimeRange);
    }
}
