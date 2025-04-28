package com.took.jpa.repository;

import com.took.jpa.entity.Comment;
import com.took.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
    void deleteById(Integer id);
}
