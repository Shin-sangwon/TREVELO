package com.ssafy.enjoytrip.member.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.global.ErrorCode;
import com.ssafy.enjoytrip.member.exception.MemberException;
import com.ssafy.enjoytrip.member.model.dto.MemberJoinDto;
import com.ssafy.enjoytrip.member.model.dto.MemberLoginDto;
import com.ssafy.enjoytrip.member.model.mapper.MemberMapper;
import com.ssafy.enjoytrip.member.model.service.MemberService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@WebMvcTest
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    MemberService memberService;
    @MockBean
    MemberMapper memberMapper;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void tearUp() throws Exception {
        MemberJoinDto memberJoinDto = MemberJoinDto.builder()
                                                  .loginId("sangwon123")
                                                  .loginPassword("1234")
                                                  .name("신상원")
                                                  .birthday(LocalDateTime.now())
                                                  .email("sangwon@ssafy.com")
                                                  .build();

        memberService.join(memberJoinDto);
    }
    @Test
    @WithMockUser
    @DisplayName("회원가입_성공한다")
    void Success_join() throws Exception {

        MemberJoinDto memberJoinDto = MemberJoinDto.builder()
                                                   .loginId("sangwon123")
                                                   .loginPassword("1234")
                                                   .name("신상원")
                                                   .birthday(LocalDateTime.now())
                                                   .email("sangwon@ssafy.com")
                                                   .build();

        mockMvc.perform(post("/api/v1/member/join")
                   .with(csrf())
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(objectMapper.writeValueAsBytes(memberJoinDto)))
               .andDo(print())
               .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName("회원가입_아이디_중복으로_실패한다")
    void Failure_join() throws Exception {

        MemberJoinDto memberJoinDto = MemberJoinDto.builder()
                                                   .loginId("sangwon123")
                                                   .loginPassword("1234")
                                                   .name("신상원")
                                                   .birthday(LocalDateTime.now())
                                                   .email("sangwon@ssafy.com")
                                                   .build();

        memberService.join(memberJoinDto);

        MemberJoinDto memberJoinDto1 = MemberJoinDto.builder()
                                                    .loginId("sangwon123")
                                                    .loginPassword("1234")
                                                    .name("신상원")
                                                    .birthday(LocalDateTime.now())
                                                    .email("sangwon@ssafy.com")
                                                    .build();

        when(memberService.join(any()))
            .thenThrow(new MemberException(ErrorCode.LOGIN_ID_DUPLICATED, "이미 존재하는 아이디입니다."));

        mockMvc.perform(post("/api/v1/member/join")
                   .with(csrf())
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(objectMapper.writeValueAsBytes(memberJoinDto1)))
               .andDo(print())
               .andExpect(status().isConflict());
    }

    @Test
    @WithMockUser
    @DisplayName("로그인_성공한다")
    void Success_login() throws Exception {

        MemberLoginDto memberLoginDto = MemberLoginDto.builder()
                                                      .loginId("sangwon123")
                                                      .loginPassword("1234")
                                                      .build();

        when(memberService.login(any()))
            .thenReturn("token");

        mockMvc.perform(post("/api/v1/member/login")
                   .with(csrf())
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(objectMapper.writeValueAsBytes(memberLoginDto)))
               .andDo(print())
               .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName("존재하지않는_ID_로그인_실패한다")
    void Failure_login_with_no_exist_id() throws Exception {

        MemberLoginDto memberLoginDto = MemberLoginDto.builder()
                                                      .loginId("rsgfa34")
                                                      .loginPassword("2423")
                                                      .build();

        when(memberService.login(any()))
            .thenThrow(new MemberException(ErrorCode.LOGIN_ID_NOT_FOUND, ""));

        mockMvc.perform(post("/api/v1/member/login")
                   .with(csrf())
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(objectMapper.writeValueAsBytes(memberLoginDto)))
               .andDo(print())
               .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    @DisplayName("잘못된_PASSWORD_로그인_실패한다")
    void Failure_login_with_wrong_password() throws Exception {

        MemberLoginDto memberLoginDto = MemberLoginDto.builder()
                                                      .loginId("sangwon123")
                                                      .loginPassword("9876")
                                                      .build();

        when(memberService.login(any()))
            .thenThrow(new MemberException(ErrorCode.INVALID_PASSWORD, ""));

        mockMvc.perform(post("/api/v1/member/login")
                   .with(csrf())
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(objectMapper.writeValueAsBytes(memberLoginDto)))
               .andDo(print())
               .andExpect(status().isUnauthorized());
    }
}