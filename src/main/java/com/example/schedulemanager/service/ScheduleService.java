package com.example.schedulemanager.service;

import com.example.schedulemanager.dto.ScheduleRequestDto;
import com.example.schedulemanager.dto.ScheduleResponseDto;
import com.example.schedulemanager.entity.Schedule;
import com.example.schedulemanager.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 생성
    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getAuthor(),
                requestDto.getPassword()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(savedSchedule);
    }

    // 전체 일정 조회
    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> getAllSchedules() {
        List<Schedule> scheduleList = scheduleRepository.findAll();

        List<ScheduleResponseDto> dtos = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            ScheduleResponseDto dto = new ScheduleResponseDto(schedule);
            dtos.add(dto);
        }
        return dtos;
    }

    // 선택 일정 조회
    @Transactional(readOnly = true)
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = findSchedule(id);
        return new ScheduleResponseDto(schedule);
    }

    // 선택 일정 수정
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = findSchedule(id);

        // 비밀번호 검증
        checkPassword(schedule.getPassword(), requestDto.getPassword());

        // 업데이트
        schedule.update(
                requestDto.getTitle(),
                requestDto.getAuthor()
        );
        return new ScheduleResponseDto(schedule);
    }

    // 선택 일정 삭제
    public void deleteSchedule(Long id, String password) {
        Schedule schedule = findSchedule(id);
        checkPassword(schedule.getPassword(), password);
        scheduleRepository.delete(schedule);
    }

    // ID로 일정 찾기
    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 일정은 존재하지 않습니다."));
    }

    // 비밀번호 확인
    private void checkPassword(String dbPassword, String inputPassword) {
        if (!dbPassword.equals(inputPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
