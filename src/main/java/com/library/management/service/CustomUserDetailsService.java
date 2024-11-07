package com.library.management.service;

import com.library.management.model.dto.MemberDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberService memberService;

    public CustomUserDetailsService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDto member = memberService.getByEmail(username);
        if (member == null) {
            throw new UsernameNotFoundException(username);
        }
        return User
                .withUsername(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole())
                .build();

    }
}
