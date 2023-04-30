package com.ssafy.enjoytrip.member.model.service;

import com.ssafy.enjoytrip.global.ErrorCode;
import com.ssafy.enjoytrip.member.exception.MemberException;
import com.ssafy.enjoytrip.member.model.dto.MemberDto;
import com.ssafy.enjoytrip.member.model.dto.MemberJoinDto;
import com.ssafy.enjoytrip.member.model.dto.MemberLoginDto;
import com.ssafy.enjoytrip.member.model.mapper.MemberMapper;
import com.ssafy.enjoytrip.security.util.JwtProvider;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.token.secret}")
    private String SecretKey;
    private final Long expireTimeMs = 1000 * 60 * 60L;
    @Transactional
    @Override
    public int join(MemberJoinDto memberJoinDto) throws Exception {

        if (joinDuplicatedCheck(memberJoinDto.getLoginId())) {
            throw new MemberException(ErrorCode.LOGIN_ID_DUPLICATED, "이미 존재하는 ID 입니다.");
        }

        MemberDto memberDto = MemberDto.from(memberJoinDto);
        memberDto.encodePassword(encoder);

        return memberMapper.join(memberDto);
    }

    @Transactional(readOnly = true)
    public boolean joinDuplicatedCheck(String loginId) throws SQLException {

        return memberMapper.joinDuplicatedCheck(loginId);
    }

    @Transactional(readOnly = true)
    @Override
    public String login(MemberLoginDto memberLoginDto) {

        MemberDto memberDto = MemberDto.from(memberLoginDto);

        MemberDto member = memberMapper.findByLoginId(memberDto.getLoginId());
        // 1. id가 없음
        if(member == null) {
            throw new MemberException(ErrorCode.LOGIN_ID_NOT_FOUND, "아이디가 존재하지 않습니다.");
        }
        // 2. 비밀번호가 일치하지 않음

        if(!encoder.matches(memberLoginDto.getLoginPassword(), member.getLoginPassword())) {
            throw new MemberException(ErrorCode.INVALID_PASSWORD, "패스워드가 일치하지 않습니다.");
        }

        String token = JwtProvider.createToken(member.getLoginId(), SecretKey, expireTimeMs);

        return token;
    }
}
