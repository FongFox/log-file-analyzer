package com.fongfox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogFileReader {
    private final LogParser logParser = new LogParser();

    public List<LogEntry> readLogFile(String filePath) {
        long startTime = System.currentTimeMillis();
        Path path = Paths.get(filePath);

        AtomicInteger totalLines = new AtomicInteger(0);
        AtomicInteger invalidLines = new AtomicInteger(0);

        try (Stream<String> lines = Files.lines(path)) {
            List<LogEntry> logEntries = lines
                    .peek(line -> totalLines.incrementAndGet())
                    // Step 1: Transform each line from a String to a LogEntry object
                    .map(logParser::parseLine)
                    .peek(entry -> {
                        if (entry == null) {
                            invalidLines.incrementAndGet();
                        }
                    })
                    // Step 2: Filter out any null results (from invalid lines or stack traces)
                    .filter(java.util.Objects::nonNull)
                    // Step 3: Collect the valid LogEntry objects into a List
                    .collect(Collectors.toList());

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            System.out.println("=== Parse Statistics ===");
            System.out.println("Total lines read: " + totalLines.get());
            System.out.println("Valid entries: " + logEntries.size());
            System.out.println("Invalid lines skipped: " + invalidLines.get());
            System.out.println("Processing time: " + duration + "ms");

            return logEntries;
        } catch (IOException e) {
            // Wrap the lower-level IOException in our custom, more specific exception
            throw new LogAnalyzerException("Failed to read log file: " + filePath, e);
        }
    }
}
