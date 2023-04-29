package com.ssafy.enjoytrip.member.model.mapper;

import com.ssafy.enjoytrip.member.model.dto.MemberDto;
import java.sql.SQLException;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    boolean joinDuplicatedCheck(String loginId) throws SQLException;

    String join(MemberDto memberDto) throws Exception;
}
