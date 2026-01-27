package com.fongfox;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@AllArgsConstructor
@Getter
@ToString
public class Report {
    private final int totalLines;
    private final Map<LogLevel, Long> levelCounts;
    private final TimeRange timeRange;
}
