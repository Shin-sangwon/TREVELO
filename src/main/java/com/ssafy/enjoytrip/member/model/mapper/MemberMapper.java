package com.ssafy.enjoytrip.member.model.mapper;

import com.ssafy.enjoytrip.member.model.dto.MemberDto;
import com.ssafy.enjoytrip.member.model.dto.InformationFindRequestDto;
import com.ssafy.enjoytrip.member.model.entity.Member;
import java.sql.SQLException;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    boolean joinDuplicatedCheck(String loginId) throws SQLException;

    int join(Member member) throws Exception;

    Optional<Member> findByLoginId(String loginId);

    MemberDto findByLoginIdAndPassword(MemberDto memberDto);

    void update(Member member);

    void signOut(String loginId);

    Optional<Member> findByLoginIdAndEmail(InformationFindRequestDto informationFindRequestDto);

    void updatePassword(Member member);
}
