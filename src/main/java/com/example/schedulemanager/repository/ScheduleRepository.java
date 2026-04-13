package com.example.schedulemanager.repository;

import com.example.schedulemanager.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> { // <엔티티 클래스, id>
}
