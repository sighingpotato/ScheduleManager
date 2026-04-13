package com.example.schedulemanager.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ScheduleRequestDto {
    private final String title;
    private final String content;
    private final String author;
    private final String password;
}
