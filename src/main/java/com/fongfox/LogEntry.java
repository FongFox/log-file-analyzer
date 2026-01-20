package com.fongfox;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class LogEntry {
    private LocalDateTime timestamp;
    private String thread;
    private LogLevel level;
    private String logger;
    private String message;
}
