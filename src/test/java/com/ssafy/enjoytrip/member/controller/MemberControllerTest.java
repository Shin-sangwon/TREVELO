package com.ssafy.enjoytrip.member.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseBody;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ssafy.enjoytrip.common.ControllerTest;
import com.ssafy.enjoytrip.global.exception.ErrorCode;
import com.ssafy.enjoytrip.member.exception.MemberException;
import com.ssafy.enjoytrip.member.model.dto.MemberJoinDto;
import com.ssafy.enjoytrip.member.model.dto.MemberLoginDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

class MemberControllerTest extends ControllerTest {

    @BeforeEach
    void tearUp() throws Exception {
        MemberJoinDto memberJoinDto = MemberJoinDto.builder()
                                                   .loginId("sangwon123")
                                                   .loginPassword("1234")
                                                   .name("신상원")
                                                   .birthday(LocalDate.from(LocalDateTime.now()))
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
                                                   .birthday(LocalDate.from(LocalDateTime.now()))
                                                   .email("sangwon@ssafy.com")
                                                   .build();

        mockMvc.perform(post("/api/v1/member/join")
                   .with(csrf())
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(objectMapper.writeValueAsBytes(memberJoinDto)))
               .andDo(
                   document("member/join",
                       preprocessRequest(prettyPrint()),
                       requestFields(
                           fieldWithPath("loginId").description("로그인 아이디"),
                           fieldWithPath("loginPassword").description("비밀번호"),
                           fieldWithPath("name").description("회원 이름"),
                           fieldWithPath("birthday").description("회원 생일").optional(),
                           fieldWithPath("email").description("회원 이메일"),
                           fieldWithPath("role").ignored(),
                           fieldWithPath("grade").ignored(),
                           fieldWithPath("mileage").ignored(),
                           fieldWithPath("createdat").ignored(),
                           fieldWithPath("updatedat").ignored()
                       ))

               )
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
                                                   .birthday(LocalDate.from(LocalDateTime.now()))
                                                   .email("sangwon@ssafy.com")
                                                   .build();

        memberService.join(memberJoinDto);

        MemberJoinDto memberJoinDto1 = MemberJoinDto.builder()
                                                    .loginId("sangwon123")
                                                    .loginPassword("1234")
                                                    .name("신상원")
                                                    .birthday(LocalDate.from(LocalDateTime.now()))
                                                    .email("sangwon@ssafy.com")
                                                    .build();

        when(memberService.join(any()))
            .thenThrow(new MemberException(ErrorCode.LOGIN_ID_DUPLICATED, "이미 존재하는 아이디입니다."));

        mockMvc.perform(post("/api/v1/member/join")
                   .with(csrf())
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON) // restdocs는 json이나 xml파일로 반환한 응답만 처리할 수 있음, rest docs와의 연동을 위해 사용
                   .content(objectMapper.writeValueAsBytes(memberJoinDto1)))
               .andDo(print())
               .andExpect(status().isConflict())
               .andDo(document("member/join-failure",
                   preprocessRequest(prettyPrint()),
                   requestFields(
                       fieldWithPath("loginId").description("로그인 아이디"),
                       fieldWithPath("loginPassword").description("비밀번호"),
                       fieldWithPath("name").description("회원 이름"),
                       fieldWithPath("birthday").description("회원 생일").optional(),
                       fieldWithPath("email").description("회원 이메일"),
                       fieldWithPath("role").ignored(),
                       fieldWithPath("grade").ignored(),
                       fieldWithPath("mileage").ignored(),
                       fieldWithPath("createdat").ignored(),
                       fieldWithPath("updatedat").ignored()
                   ),
//                   responseFields(
//                       fieldWithPath("error").description("에러 코드")
//                   ), -> 나의 경우에는 Body에 Custom errorCode와 message를 함께 담아서 보내기 때문에, 아래의 responseBody()를 사용해야 함
                   responseBody()
               ));
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