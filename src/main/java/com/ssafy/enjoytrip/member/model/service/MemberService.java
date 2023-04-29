package com.ssafy.enjoytrip.member.model.service;

import com.ssafy.enjoytrip.member.model.dto.MemberJoinDto;
import java.sql.SQLException;

public interface MemberService {

    String join(MemberJoinDto memberJoinDto) throws Exception;

    boolean joinDuplicatedCheck(String loginId) throws SQLException;
}
