package com.fongfox;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LogFileReader reader = new LogFileReader();
        String logFilePath = "src/main/resources/sample-logs/sample.log";

        try {
            System.out.println("Reading log file: " + logFilePath);
            List<LogEntry> entries = reader.readLogFile(logFilePath);

            System.out.println("Successfully read " + entries.size() + " valid log entries.");
            System.out.println("--- First 5 entries ---");

            for (int i = 0; i < 5 && i < entries.size(); i++) {
                System.out.println(entries.get(i));
            }

            LogAnalyzer analyzer = new LogAnalyzer(entries);
            Report report = analyzer.createReport();

            Reporter reporter = new Reporter();
            Path outputPath = Paths.get("src/main/resources/output/txt/report.txt");
            reporter.writeToTextFile(report, outputPath);
            System.out.println("Report written to " + outputPath.toAbsolutePath());

            Path jsonOut = Paths.get("src/main/resources/output/json/report.json");
            reporter.writeToJsonFile(report, jsonOut);
            System.out.println("JSON report written to " + jsonOut.toAbsolutePath());

        } catch (LogAnalyzerException e) {
            System.err.println("Error processing log file: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
