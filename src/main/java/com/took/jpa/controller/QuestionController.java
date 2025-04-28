package com.took.jpa.controller;

import com.took.jpa.dto.CustomUserDetails;
import com.took.jpa.dto.QuestionDto;
import com.took.jpa.entity.Question;
import com.took.jpa.service.QuestionService;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
@Slf4j
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/write")
    public String write() {
        return "/question/write";
    }

    @PostMapping("/write")
//    @ResponseBody
    public String write(@ModelAttribute QuestionDto questionDto, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
//        SecurityContextHolder.getContext().setAuthentication() 이거 대신 @AuthenticationPrincipal 이거 씀
        questionDto.setWriter(customUserDetails.getLoggedMember());
        questionService.write(questionDto);
        return "redirect:/question/list";
    }

    @GetMapping("/list")
//    @ResponseBody
    public String list(Model model) {
        List<Question> list = questionService.list();
        model.addAttribute("list", list);
        return "/question/list";
    }

    @GetMapping("/view/{id}")
//    @ResponseBody
    public String view(@PathVariable("id") Integer id, Model model) {
        //프론트에서 넘어오는거는 dto로 받아서 service로 던져주면 entity로 바꿔서 저장
        Question question = questionService.view(id);
        QuestionDto questionDto = null;
        if (question != null) {
//            questionDto = QuestionDto.builder()
//                    .id(question.getId())
//                    .title(question.getTitle())
//                    .content(question.getContent())
//                    .regDate(question.getRegDate())
//                    .build();
            questionDto = Question.toDto(question);
//            log.info(questionDto.getCommentList().get(0).getContent());
        }
        model.addAttribute("questionDto", questionDto);
//        log.info(questionDto.toString());
        return "/question/view";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Integer id, Model model) {
        Question question = questionService.view(id);
        QuestionDto questionDto = null;
        if (question != null) {
            questionDto = Question.toDto(question);
        }
        model.addAttribute("questionDto", questionDto);
        return "/question/modify";
    }

    @PostMapping("/modify")
    public String modify(@ModelAttribute QuestionDto questionDto) {
        Question question = questionService.modify(questionDto);
        if (question != null) {
            return "redirect:/question/list";
        }
        return "redirect:/question/modify"+questionDto.getId();
    }
}
