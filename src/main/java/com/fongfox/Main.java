package com.fongfox;

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

        } catch (LogAnalyzerException e) {
            System.err.println("Error processing log file: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
