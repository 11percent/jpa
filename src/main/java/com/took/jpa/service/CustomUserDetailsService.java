package com.took.jpa.service;

import com.took.jpa.dto.CustomUserDetails;
import com.took.jpa.dto.MemberDto;
import com.took.jpa.entity.Member;
import com.took.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String userID) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByUserID(userID);//아이디만 가지고 찾은 dto 여기에는 모든 정보가 다 들어가 있다.
        if (optionalMember.isPresent()) {
            return new CustomUserDetails(optionalMember.get());
        }
        throw new UsernameNotFoundException("아이디 패스워드를 확인해 주세요.");
    }
}
