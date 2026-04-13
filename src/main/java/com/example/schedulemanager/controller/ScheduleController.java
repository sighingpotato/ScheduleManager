package com.example.schedulemanager.controller;

import com.example.schedulemanager.dto.ScheduleRequestDto;
import com.example.schedulemanager.dto.ScheduleResponseDto;
import com.example.schedulemanager.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    //일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto response = scheduleService.createSchedule(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // 201 Created
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules() {
        List<ScheduleResponseDto> response = scheduleService.getAllSchedules();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 선택 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id) {
        ScheduleResponseDto response = scheduleService.getSchedule(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 선택 일정 수정
    @PatchMapping
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto response = scheduleService.updateSchedule(id, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 선택 일정 삭제
    @DeleteMapping
    public ResponseEntity<ScheduleResponseDto> deleteSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto requestDto) {
        scheduleService.deleteSchedule(id, requestDto.getPassword());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
    }
}
