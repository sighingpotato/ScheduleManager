package com.example.schedulemanager.repository;

import com.example.schedulemanager.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    long countByScheduleId(Long scheduleId); // 10개 제한을 위한 특정한 일정에 달린 댓글 개수를 세는 것
}
