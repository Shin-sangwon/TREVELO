package com.ssafy.enjoytrip.member.model.service;

import com.ssafy.enjoytrip.global.ErrorCode;
import com.ssafy.enjoytrip.member.exception.MemberException;
import com.ssafy.enjoytrip.member.model.dto.MemberDto;
import com.ssafy.enjoytrip.member.model.dto.MemberJoinDto;
import com.ssafy.enjoytrip.member.model.dto.MemberLoginDto;
import com.ssafy.enjoytrip.member.model.mapper.MemberMapper;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder encoder;

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
        memberDto.encodePassword(encoder);
        // 1. id가 없음
        if(memberMapper.findByLoginId(memberDto.getLoginId()) == 0) {
            throw new MemberException(ErrorCode.LOGIN_ID_NOT_FOUND, "아이디가 존재하지 않습니다.");
        }

        MemberDto member = memberMapper.findByLoginIdAndPassword(memberDto);
        // 2. password가 일치하지 않음
        if(member == null || encoder.matches(member.getLoginPassword(), memberDto.getLoginPassword())) {
            throw new MemberException(ErrorCode.INVALID_PASSWORD, "패스워드가 일치하지 않습니다.");
        }

        return "token";
    }
}
