package com.fongfox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogFileReader {
    private final LogParser logParser = new LogParser();

    public List<LogEntry> readLogFile(String filePath) {
        Path path = Paths.get(filePath);

        try (Stream<String> lines = Files.lines(path)) {
            return lines
                    // Step 1: Transform each line from a String to a LogEntry object
                    .map(logParser::parseLine)
                    // Step 2: Filter out any null results (from invalid lines or stack traces)
                    .filter(java.util.Objects::nonNull)
                    // Step 3: Collect the valid LogEntry objects into a List
                    .collect(Collectors.toList());
        } catch (IOException e) {
            // Wrap the lower-level IOException in our custom, more specific exception
            throw new LogAnalyzerException("Failed to read log file: " + filePath, e);
        }
    }
}
