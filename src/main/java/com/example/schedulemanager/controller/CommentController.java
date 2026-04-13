package com.example.schedulemanager.controller;

import com.example.schedulemanager.dto.CommentRequestDto;
import com.example.schedulemanager.dto.CommentResponseDto;
import com.example.schedulemanager.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules/{scheduleId}/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable Long scheduleId,
            @RequestBody CommentRequestDto requestDto) {
        return new ResponseEntity<>(commentService.createComment(scheduleId, requestDto), HttpStatus.CREATED);
    }
}
