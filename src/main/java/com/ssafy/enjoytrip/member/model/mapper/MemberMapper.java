package com.ssafy.enjoytrip.member.model.mapper;

import com.ssafy.enjoytrip.member.model.dto.MemberDto;
import java.sql.SQLException;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    boolean joinDuplicatedCheck(String loginId) throws SQLException;

    int join(MemberDto memberDto) throws Exception;

    MemberDto findByLoginId(String loginId);

    MemberDto findByLoginIdAndPassword(MemberDto memberDto);
}
