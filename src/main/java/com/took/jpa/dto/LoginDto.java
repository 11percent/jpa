package com.took.jpa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LoginDto {
    @NotBlank(message = "아이디는 필수 입력 사항 입니다.")
    private String userID;

    @NotBlank(message = "패스워드는 필수 입력 사항 입니다.")
    private String userPW;
}