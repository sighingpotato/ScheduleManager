package com.example.schedulemanager.dto;

import com.example.schedulemanager.entity.Comment;
import com.example.schedulemanager.entity.Schedule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ScheduleResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final List<CommentResponseDto> comments;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.author = schedule.getAuthor();

        this.comments = new ArrayList<>();
        for (Comment comment : schedule.getComments()) {
            CommentResponseDto dto = new CommentResponseDto(comment);
            this.comments.add(dto);
        }
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}
