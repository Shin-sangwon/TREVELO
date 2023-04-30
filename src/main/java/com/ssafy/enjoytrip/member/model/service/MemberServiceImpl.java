package com.ssafy.enjoytrip.member.model.service;

import com.ssafy.enjoytrip.global.ErrorCode;
import com.ssafy.enjoytrip.member.exception.MemberException;
import com.ssafy.enjoytrip.member.model.dto.MemberJoinDto;
import com.ssafy.enjoytrip.member.model.dto.MemberLoginDto;
import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.member.model.mapper.MemberMapper;
import com.ssafy.enjoytrip.security.util.JwtProvider;
import java.sql.SQLException;
import java.util.Optional;
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

        Member member = Member.from(memberJoinDto);
        member.encodePassword(encoder);

        return memberMapper.join(member);
    }

    @Transactional(readOnly = true)
    public boolean joinDuplicatedCheck(String loginId) throws SQLException {

        return memberMapper.joinDuplicatedCheck(loginId);
    }

    @Transactional(readOnly = true)
    @Override
    public String login(MemberLoginDto memberLoginDto) {

        Optional<Member> member = memberMapper.findByLoginId(memberLoginDto.getLoginId());
        // 1. id가 없음
        if (!member.isPresent()) {
            throw new MemberException(ErrorCode.LOGIN_ID_NOT_FOUND, "아이디가 존재하지 않습니다.");
        }
        // 2. 비밀번호가 일치하지 않음

        if (!encoder.matches(memberLoginDto.getLoginPassword(), member.get()
                                                                      .getLoginPassword())) {
            throw new MemberException(ErrorCode.INVALID_PASSWORD, "패스워드가 일치하지 않습니다.");
        }

        return JwtProvider.createToken(member.get()
                                             .getLoginId(), SecretKey, expireTimeMs);
    }

    @Override
    public Member findByLoginId(String loginId) throws Exception {
        return memberMapper.findByLoginId(loginId)
                           .orElseThrow(() -> new MemberException(ErrorCode.LOGIN_ID_NOT_FOUND,
                               "아이디가 존재하지 않습니다."));
    }

}
