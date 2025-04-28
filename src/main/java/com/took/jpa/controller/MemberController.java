package com.took.jpa.controller;

import com.took.jpa.dto.MemberDto;
import com.took.jpa.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/signup";
    }

    @PostMapping("/signup")
//    @ResponseBody
    public String signup(@Valid @ModelAttribute MemberDto memberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/signup"; //포워드
        }
//        System.out.println(memberDto.toString());

        //service 영역을 business login 여기가 핵심이다.
        int result = memberService.signUp(memberDto);
        log.info(result + "");
        if (result > 0) {
            return "redirect:/";
        }
        return "member/signup";
    }

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }
}
