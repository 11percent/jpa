package com.took.jpa.controller;

import com.took.jpa.dto.CommentDto;
import com.took.jpa.dto.CustomUserDetails;
import com.took.jpa.dto.MemberDto;
import com.took.jpa.entity.Comment;
import com.took.jpa.entity.Member;
import com.took.jpa.entity.Question;
import com.took.jpa.repository.MemberRepository;
import com.took.jpa.service.CommentService;
import com.took.jpa.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final QuestionService questionService;
    private final MemberRepository memberRepository;

    @PostMapping("/write/{id}")
    public String write(@PathVariable("id") int id, @RequestParam String content, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Question question = questionService.view(id);
        Member writer = userDetails.getLoggedMember();
        commentService.write(content, question, writer);
        return "redirect:/question/view/" + id;
    }

    @PostMapping("/write-ajax/{id}")
    @ResponseBody
    public Map<String, Object> writeAjax(@PathVariable("id") int id,
                                         @RequestBody CommentDto commentDto,
                                         @AuthenticationPrincipal CustomUserDetails userDetails) {
        Question question = questionService.view(id);
        Member writer = userDetails.getLoggedMember();
        Comment comment = commentService.write(commentDto.getContent(), question, writer);

        Map<String, Object> result = new HashMap<>();

        CommentDto responseCommentDto = CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .writerName(comment.getWriter().getUserName())
                .regDate(comment.getRegDate())
                .build();

        result.put("comment", responseCommentDto);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Integer id,
                                      @AuthenticationPrincipal CustomUserDetails userDetails){
        Comment comment = commentService.findById(id).orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
        Member loginUser = userDetails.getLoggedMember();
        if (!comment.getWriter().getId().equals(loginUser.getId())) {
            return "fail";
        }
        commentService.deleteById(id);
        return "ok";
    }

}
