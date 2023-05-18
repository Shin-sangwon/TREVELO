package com.ssafy.enjoytrip.member.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ssafy.enjoytrip.global.service.AmazonS3Service;
import com.ssafy.enjoytrip.member.exception.MemberException;
import com.ssafy.enjoytrip.member.model.dto.MemberJoinDto;
import com.ssafy.enjoytrip.member.model.dto.MemberLoginDto;
import com.ssafy.enjoytrip.member.model.entity.Grade;
import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.member.model.entity.Role;
import com.ssafy.enjoytrip.member.model.mapper.MemberMapper;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberMapper memberMapper;
    @MockBean
    AmazonS3Service amazonS3Service;

    @Test
    @Transactional
    @DisplayName("회원가입 - 성공한다")
    void memberJoin_Success() throws Exception {

        MemberJoinDto memberJoinDto = MemberJoinDto.builder()
                                                   .loginId("test1")
                                                   .loginPassword("test1")
                                                   .name("테스트")
                                                   .birthday(LocalDateTime.now())
                                                   .email("test1@test.com")
                                                   .role(Role.ADMIN)
                                                   .grade(Grade.VIP)
                                                   .build();

        memberService.join(memberJoinDto);

        Member member = memberMapper.findByLoginId(memberJoinDto.getLoginId())
                                    .orElseThrow(RuntimeException::new);

        assertNotNull(member);
        assertEquals(member.getLoginId(), memberJoinDto.getLoginId());
    }

    @Test
    @Transactional
    @DisplayName("회원가입 - 아이디_중복으로_실패한다")
    void memberJoin_Failure_With_Id_Duplicated() throws Exception {

        MemberJoinDto memberJoinDto1 = MemberJoinDto.builder()
                                                    .loginId("test123")
                                                    .loginPassword("test123")
                                                    .name("테스트")
                                                    .birthday(LocalDateTime.now())
                                                    .email("test123@test.com")
                                                    .role(Role.ADMIN)
                                                    .grade(Grade.VIP)
                                                    .build();

        MemberJoinDto memberJoinDto2 = MemberJoinDto.builder()
                                                    .loginId("test123")
                                                    .loginPassword("test123")
                                                    .name("테스트")
                                                    .birthday(LocalDateTime.now())
                                                    .email("test123@test.com")
                                                    .role(Role.ADMIN)
                                                    .grade(Grade.VIP)
                                                    .build();

        memberService.join(memberJoinDto1);

        assertThrows(MemberException.class, () -> memberService.join(memberJoinDto2));
    }

    @Test
    @Transactional
    @DisplayName("로그인 - 성공한다")
    void memberLogin_Success() throws Exception {

        MemberJoinDto memberJoinDto = MemberJoinDto.builder()
                                                    .loginId("test123")
                                                    .loginPassword("test123")
                                                    .name("테스트")
                                                    .birthday(LocalDateTime.now())
                                                    .email("test123@test.com")
                                                    .role(Role.ADMIN)
                                                    .grade(Grade.VIP)
                                                    .build();

        memberService.join(memberJoinDto);

        MemberLoginDto memberLoginDto = MemberLoginDto.builder()
                                                      .loginId("test123")
                                                      .loginPassword("test123")
                                                      .build();

        String token = memberService.login(memberLoginDto);

        System.out.printf("JWT TOKEN : %s\n", token);
        assertNotNull(token);
    }

    @Test
    @Transactional
    @DisplayName("로그인 - 없는_아이디_로그인_실패한다")
    void memberLogin_Failure_With_LOGIN_ID_NOT_FOUND_EXCEPTION() throws Exception {

        MemberJoinDto memberJoinDto = MemberJoinDto.builder()
                                                   .loginId("test123")
                                                   .loginPassword("test123")
                                                   .name("테스트")
                                                   .birthday(LocalDateTime.now())
                                                   .email("test123@test.com")
                                                   .role(Role.ADMIN)
                                                   .grade(Grade.VIP)
                                                   .build();

        memberService.join(memberJoinDto);

        MemberLoginDto memberLoginDto = MemberLoginDto.builder()
                                                      .loginId("test123")
                                                      .loginPassword("test1234")
                                                      .build();

        assertThrows(MemberException.class, () -> memberService.login(memberLoginDto));
    }

    @Test
    @Transactional
    @DisplayName("로그인 - 비밀번호가_일치하지_않아서_실패한다")
    void memberLogin_Failure_With_INVALID_PASSWORD() throws Exception {

        MemberLoginDto memberLoginDto = MemberLoginDto.builder()
                                                      .loginId("test123")
                                                      .loginPassword("test1234")
                                                      .build();

        assertThrows(MemberException.class, () -> memberService.login(memberLoginDto));
    }

    @Test
    @Transactional
    @DisplayName("findByLoginId - 성공한다")
    void findByLoginId_Success() throws Exception {

        MemberJoinDto memberJoinDto = MemberJoinDto.builder()
                                                   .loginId("test123")
                                                   .loginPassword("test123")
                                                   .name("테스트")
                                                   .birthday(LocalDateTime.now())
                                                   .email("test123@test.com")
                                                   .role(Role.ADMIN)
                                                   .grade(Grade.VIP)
                                                   .build();

        memberService.join(memberJoinDto);

        Member member = memberService.findByLoginId(memberJoinDto.getLoginId());

        assertNotNull(member);
        assertEquals(member.getGrade(), memberJoinDto.getGrade());

    }

    @Test
    @Transactional
    @DisplayName("findByLoginId - 없는_아이디로_요청하면_실패한다")
    void findByLoginId_Faliure_With_LOGIN_ID_NOT_FOUND() throws Exception {

        assertThrows(MemberException.class, () -> memberService.findByLoginId("nothing"));

    }
}