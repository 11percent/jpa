package com.took.jpa.service;

import com.took.jpa.entity.Comment;
import com.took.jpa.entity.Member;
import com.took.jpa.entity.Question;
import com.took.jpa.repository.CommentRepository;
import com.took.jpa.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CommentService {
    private final CommentRepository commentRepository;

    public Comment write(String content, Question question, Member writer) {
        Comment comment = Comment.builder()
                .content(content)
                .question(question)
                .writer(writer)
                .build();
        return commentRepository.save(comment);
    }

    public void deleteById(Integer id) {
        commentRepository.deleteById(id);
    }

    public Optional<Comment> findById(Integer id) {
        return commentRepository.findById(id);
    }
}
