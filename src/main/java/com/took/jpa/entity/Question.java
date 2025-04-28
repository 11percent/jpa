package com.took.jpa.entity;

import com.took.jpa.dto.QuestionDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
//@Setter
@NoArgsConstructor
//@Table(name="table_question")
@ToString

public class Question extends BaseEntity {

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private Member writer;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String title;
    private String content;


    @Builder
    public Question(Integer id, String title, String content, Member writer, List<Comment> commentList, LocalDateTime regDate, LocalDateTime modifyDate) {
        this.id = id;
        this.title=title;
        this.content=content;
        this.writer=writer;
        this.regDate=regDate;
        this.modifyDate=modifyDate;
        this.commentList = commentList;
    }

    public static QuestionDto toDto(Question question) {
        QuestionDto questionDto = QuestionDto.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .commentList(question.getCommentList())
                .regDate(question.getRegDate())
                .modifyDate(question.getModifyDate())
                .build();
        return questionDto;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
