package com.example.schedulemanager.service;

import com.example.schedulemanager.dto.CommentRequestDto;
import com.example.schedulemanager.dto.CommentResponseDto;
import com.example.schedulemanager.entity.Comment;
import com.example.schedulemanager.entity.Schedule;
import com.example.schedulemanager.repository.CommentRepository;
import com.example.schedulemanager.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentResponseDto createComment(Long scheduleId, CommentRequestDto requestDto) {
        // 일정 존재 확인
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        // 댓글 개수 10개 제한
        long commentCount = commentRepository.countByScheduleId(scheduleId);
        if (commentCount >= 10) {
            throw new IllegalArgumentException("한개의 일정에는 댓글을 10개까지만 작성할 수 있습니다.");
        }

        // 댓글 저장
        Comment comment = new Comment(requestDto, schedule);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }
}
