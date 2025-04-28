package com.took.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Table(name = "comments")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String content;

    @OneToMany
    private List<Comment> commentList;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="questionNum")
    @JsonIgnore
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Member writer;

    @JsonProperty("writerName")
    public String getWriterName() {
        return writer != null ? writer.getUserName() : null;
    }

    @Builder
    public Comment(Integer id, String content, Question question, Member writer) {
        this.id = id;
        this.content = content;
        this.question = question;
        this.writer = writer;
    }
}
