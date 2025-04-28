package com.took.jpa.dto;

import com.took.jpa.entity.Member;
import com.took.jpa.entity.Question;
import lombok.*;

import java.security.PrivateKey;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private int id;
    private String content;
    private Question question;
    private Member member;
    private String writerName;
    private LocalDateTime regDate;
    private LocalDateTime modifyDate;

}
