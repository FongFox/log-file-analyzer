package com.fongfox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    // TODO: Khai báo Pattern ở đây (static final)
    private static final Pattern regexPattern
            = Pattern.compile("(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}) \\[([^\\]]+)\\] (\\w+)\\s+(\\w+) - (.*)");
    private static final DateTimeFormatter dateTimeFormatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public LogEntry parseLine(String logLine) {
        // TODO: Implement logic
        // 1. Dùng regex match
        // TODO 1: Tạo Matcher từ Pattern
        Matcher matcher = regexPattern.matcher(logLine);
        // TODO 2: Kiểm tra xem có match không?
        if(matcher.matches()) {
            // Nếu match → tiếp tục parse
            String timeString = matcher.group(1);
            LocalDateTime timestamp = LocalDateTime.parse(timeString, dateTimeFormatter);

            String thread = matcher.group(2);

            String levelString = matcher.group(3);
            LogLevel logLevel = LogLevel.valueOf(levelString);

            String logger = matcher.group(4);
            String message = matcher.group(5);

            return new LogEntry(timestamp, thread, logLevel, logger, message);
        } else {
            // Nếu không match → làm gì?
            return null; // Tạm thời return null
        }

        // 2. Extract groups
        // 3. Convert timestamp
        // 4. Convert level
        // 5. Return new LogEntry(...)

        // return null;  // Placeholder
    }
}
