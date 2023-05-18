package com.ssafy.enjoytrip.member.model.service;

import com.ssafy.enjoytrip.member.model.dto.MemberJoinDto;
import com.ssafy.enjoytrip.member.model.dto.MemberLoginDto;
import com.ssafy.enjoytrip.member.model.dto.MemberUpdateDto;
import com.ssafy.enjoytrip.member.model.dto.InformationFindRequestDto;
import com.ssafy.enjoytrip.member.model.entity.Member;
import java.sql.SQLException;

public interface MemberService {

    int join(MemberJoinDto memberJoinDto) throws Exception;

    boolean joinDuplicatedCheck(String loginId) throws SQLException;

    String login(MemberLoginDto memberLoginDto);

    Member findByLoginId(String loginId);

    Member update(MemberUpdateDto memberUpdateDto) throws Exception;

    void signOut(String loginId);

    String findPassword(InformationFindRequestDto informationFindRequestDto);

    String findLoginId(InformationFindRequestDto informationFindRequestDto);

    void deductMileage(Member member);
}
