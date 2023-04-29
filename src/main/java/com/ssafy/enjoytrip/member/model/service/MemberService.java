package com.ssafy.enjoytrip.member.model.service;

import com.ssafy.enjoytrip.member.model.dto.MemberJoinDto;
import com.ssafy.enjoytrip.member.model.dto.MemberLoginDto;
import java.sql.SQLException;

public interface MemberService {

    int join(MemberJoinDto memberJoinDto) throws Exception;

    boolean joinDuplicatedCheck(String loginId) throws SQLException;

    String login(MemberLoginDto memberLoginDto);
}
