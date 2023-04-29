package com.ssafy.enjoytrip.member.model.service;

import com.ssafy.enjoytrip.member.model.dto.MemberDto;
import com.ssafy.enjoytrip.member.model.dto.MemberJoinDto;
import com.ssafy.enjoytrip.member.model.entity.Grade;
import com.ssafy.enjoytrip.member.model.entity.Role;
import com.ssafy.enjoytrip.member.model.mapper.MemberMapper;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Transactional
    @Override
    public String join(MemberJoinDto memberJoinDto) throws Exception {

        if (joinDuplicatedCheck(memberJoinDto.getLoginId())) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        MemberDto memberDto = MemberDto.builder()
                                       .loginId(memberJoinDto.getLoginId())
                                       .loginPassword(memberJoinDto.getLoginPassword())
                                       .name(memberJoinDto.getName())
                                       .birthday(memberJoinDto.getBirthday())
                                       .email(memberJoinDto.getEmail())
                                       .role(Role.MEMBER)
                                       .grade(Grade.GENERAL)
                                       .mileage(0)
                                       .createdat(memberJoinDto.getCreatedat())
                                       .updatedat(memberJoinDto.getUpdatedat())
                                       .build();

        memberMapper.join(memberDto);

        return "success";
    }

    @Transactional(readOnly = true)
    public boolean joinDuplicatedCheck(String loginId) throws SQLException {

        return memberMapper.joinDuplicatedCheck(loginId);
    }
}
