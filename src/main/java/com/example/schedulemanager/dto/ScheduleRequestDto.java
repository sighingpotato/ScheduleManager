package com.example.schedulemanager.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ScheduleRequestDto {
    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 30, message = "최대 30자까지 입력할 수 있습니다.")
    private final String title;

    @NotBlank(message = "내용은 필수입니다.")
    @Size(max = 200, message = "최대 200자까지 입력할 수 있습니다.")
    private final String content;

    @NotBlank(message = "작성자명은 필수입니다.")
    private final String author;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private final String password;
}
