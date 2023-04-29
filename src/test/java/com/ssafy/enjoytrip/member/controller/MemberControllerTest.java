package com.ssafy.enjoytrip.member.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.member.model.dto.MemberJoinDto;
import com.ssafy.enjoytrip.member.model.mapper.MemberMapper;
import com.ssafy.enjoytrip.member.model.service.MemberService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

    @Test
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
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(objectMapper.writeValueAsBytes(memberJoinDto)))
               .andDo(print())
               .andExpect(status().isOk());
    }

    @Test
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

        when(memberService.join(memberJoinDto1))
            .thenThrow(new RuntimeException("이미 존재하는 아이디입니다."));

        mockMvc.perform(post("/api/v1/member/join")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(objectMapper.writeValueAsBytes(memberJoinDto1)))
               .andDo(print())
               .andExpect(status().isConflict());
    }
}