package com.took.jpa.service;

import com.took.jpa.dto.MemberDto;
import com.took.jpa.entity.Member;
import com.took.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    //필드 주입
//    @Autowired
//    MemberDao memberDao;
    @Value("${file.upload}")
    private String upload;

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public int signUp(MemberDto memberDto) {
        String rawUserPw = memberDto.getUserPW();
        String encodedUserPw = bCryptPasswordEncoder.encode(rawUserPw);
        memberDto.setUserPW(encodedUserPw);
        memberDto.setRoles(("ROLE_MEMBER"));
        Member member = MemberDto.toEntity(memberDto);
        Member returnMember = memberRepository.save(member);
        if (returnMember != null) {
            return 1;
        }
        return 0;
    }
}
