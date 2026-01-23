package com.fongfox;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class LogParserTest {
    private LogParser logParser;

    @BeforeEach
    void setUp() {
        logParser = new LogParser();
    }

    @Test
    void testParseLine_ValidInfoLog_ShouldReturnLogEntry() {
        String logLine = "2026-01-20 12:40:01.123 [main] INFO  AppStarter - Starting application";

        LogEntry result = logParser.parseLine(logLine);

        assertNotNull(result);
        assertEquals(
                LocalDateTime.of(2026, 1, 20, 12, 40, 1, 123000000),
                result.getTimestamp()
        );
        assertEquals("main", result.getThread());
        assertEquals(LogLevel.INFO, result.getLevel());
        assertEquals("AppStarter", result.getLogger());
        assertEquals("Starting application", result.getMessage());
    }

    @Test
    void testParseLine_ValidErrorLog_ShouldReturnLogEntry() {
        String logLine = "2026-01-20 12:42:45.880 [http-nio-8080-exec-4] ERROR OrderController - Unexpected error";

        LogEntry result = logParser.parseLine(logLine);

        assertNotNull(result);
        assertEquals(
                LocalDateTime.of(2026, 1, 20, 12, 42, 45, 880000000),
                result.getTimestamp()
        );
        assertEquals("http-nio-8080-exec-4", result.getThread());
        assertEquals(LogLevel.ERROR, result.getLevel());
        assertEquals("OrderController", result.getLogger());
        assertEquals("Unexpected error", result.getMessage());
    }

    @Test
    void testParseLine_InvalidLogLine_ShouldReturnNull() {
        String logLine = "This is not a valid log line";

        LogEntry result = logParser.parseLine(logLine);

        assertNull(result);
    }

    @Test
    void testParseLine_EmptyLine_ShouldReturnNull() {
        String logLine = "";

        LogEntry result = logParser.parseLine(logLine);

        assertNull(result);
    }

    @Test
    void testParseLine_ThreadNameWithHyphens_ShouldParse() {
        String logLine = "2026-01-20 12:42:45.880 [http-nio-8080-exec-4] WARN SomeLogger - Test message";

        LogEntry result = logParser.parseLine(logLine);

        assertNotNull(result);
        assertEquals("http-nio-8080-exec-4", result.getThread());
        assertEquals(LogLevel.WARN, result.getLevel());
    }
}
