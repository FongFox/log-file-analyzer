package com.fongfox;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // 1. Kiểm tra điều kiện dừng sớm (help/empty)
        if (args.length == 0 || args[0].equals("--help")) {
            printUsage();
            return;
        }

        // 2. Khởi tạo biến từ args[0] và các giá trị mặc định
        String logFilePath = args[0];
        String outputFormat = "console";
        String outputFile = null;
        String levelFilter = null;

        // 3. Vòng lặp parse (Phần bạn đang viết)
        for (int i = 1; i < args.length; i++) {
            switch (args[i]) {
                case "--output":
                    if (i + 1 < args.length) outputFormat = args[++i].toLowerCase();
                    break;
                case "--file":
                    if (i + 1 < args.length) {
                        outputFile = args[++i]; // Lấy đường dẫn file user nhập
                    }
                    break;
                case "--level":
                    if (i + 1 < args.length) levelFilter = args[++i].toUpperCase();
                    break;
            }
        }

        // 4. Logic thực thi (Cải tiến từ code cũ của bạn)
        try {
            LogFileReader reader = new LogFileReader();
            List<LogEntry> entries = reader.readLogFile(logFilePath);

            if (levelFilter != null) {
                try {
                    // 1. Cố gắng chuyển đổi chuỗi thành Enum
                    LogLevel targetLevel = LogLevel.valueOf(levelFilter);

                    // 2. Nếu thành công, thực hiện lọc danh sách
                    entries = entries.stream()
                            .filter(entry -> entry.getLevel() == targetLevel)
                            .collect(Collectors.toList());

                    System.out.println("Filtered entries for level: " + targetLevel + ". Remaining: " + entries.size());
                } catch (IllegalArgumentException e) {
                    // 3. Nếu người dùng nhập sai, thông báo lỗi và dừng chương trình một cách lịch sự
                    System.err.println("Error: Invalid log level '" + levelFilter + "'.");
                    System.err.println("Supported levels are: DEBUG, INFO, WARN, ERROR, FATAL");
                    return; // Dừng hàm main
                }
            }

            LogAnalyzer analyzer = new LogAnalyzer(entries);
            Report report = analyzer.createReport();
            Reporter reporter = new Reporter();

            // 5. Xuất kết quả dựa trên outputFormat
            // Thay vì chạy cả 3 như cũ, ta dùng switch-case cho outputFormat
            switch (outputFormat) {
                case "text":
                    // Dùng outputFile nếu có, nếu không thì dùng mặc định
                    String finalTxtPath = (outputFile != null) ? outputFile : "src/main/resources/output/txt/report.txt";
                    Path txtPath = Paths.get(finalTxtPath);

                    reporter.writeToTextFile(report, txtPath);
                    System.out.println("Report written to " + txtPath.toAbsolutePath());
                    break;
                case "json":
                    String finalJsonPath = (outputFile != null) ? outputFile : "src/main/resources/output/json/report.json";
                    Path jsonOut = Paths.get(finalJsonPath);

                    reporter.writeToJsonFile(report, jsonOut);
                    System.out.println("JSON report written to " + jsonOut.toAbsolutePath());
                    break;
                default:
                    reporter.printToConsole(report);
                    break;
            }

        } catch (Exception e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }

    private static void printUsage() {
        System.out.println("Log File Analyzer - Professional CLI tool for log insights");
        System.out.println();
        System.out.println("Usage: java -jar log-analyzer.jar <path_to_log_file> [options]");
        System.out.println();
        System.out.println("Arguments:");
        System.out.println("  <path_to_log_file>    Path to the source log file (Required)");
        System.out.println();
        System.out.println("Options:");
        System.out.println("  --output <format>     Output format: console, text, json (Default: console)");
        System.out.println("  --file <path>         Path to save the output file (For text/json formats)");
        System.out.println("  --level <LEVEL>       Filter by severity: DEBUG, INFO, WARN, ERROR, FATAL");
        System.out.println("  --help, -h            Show this help message");
        System.out.println();
        System.out.println("Examples:");
        System.out.println("  java -jar log-analyzer.jar app.log --level ERROR --output json");
        System.out.println("  java -jar log-analyzer.jar system.log --output text --file ./reports/daily.txt");
    }
}