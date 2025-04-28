package com.took.jpa.dto;

import com.took.jpa.entity.Member;
import com.took.jpa.entity.Question;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MemberDto {

    private int no;

    @NotBlank(message = "아이디는 필수 입력 사항 입니다.")
    private String userID;

    @NotBlank(message = "패스워드는 필수 입력 사항 입니다.")
    private String userPW; //암호화 시켜서 넣을 예정

    @NotBlank(message = "이릉은 필수 입력 사항 입니다.")
    private String userName;

    @Email(message = "이메일 형식에 맞게 입력")
    private String userEmail;

    private String tel;
    private String address01;
    private String address02;

    @Size(min = 5, max = 6, message = "5자리 숫자로 써주세요.")
    private String zipcode;

//    private MultipartFile profile;

    private String originalProfile; //db에 넣을 거
    private String renameProfile;
    private String roles;
    private String regdate;

    public static Member toEntity(MemberDto memberDto) {
        return Member.builder()
                .userID(memberDto.getUserID())
                .userName(memberDto.getUserName())
                .userPW(memberDto.getUserPW())
                .userEmail(memberDto.getUserEmail())
                .address01(memberDto.getAddress01())
                .address02(memberDto.getAddress02())
                .zipcode(memberDto.getZipcode())
                .tel(memberDto.getTel())
                .roles(memberDto.getRoles())
                .build();
    }

}


