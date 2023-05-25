package com.ssafy.enjoytrip.security.service;

import com.ssafy.enjoytrip.member.model.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        return memberMapper.findByLoginId(loginId)
                           .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));
    }
}
