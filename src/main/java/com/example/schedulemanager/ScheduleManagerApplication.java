package com.example.schedulemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class ScheduleManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleManagerApplication.class, args);
    }

}
