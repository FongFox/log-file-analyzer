package com.fongfox;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class TimeRange {
    private final LocalDateTime start;
    private final LocalDateTime end;
}
