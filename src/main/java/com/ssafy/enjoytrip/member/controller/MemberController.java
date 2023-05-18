package com.ssafy.enjoytrip.member.controller;

import com.ssafy.enjoytrip.global.ErrorCode;
import com.ssafy.enjoytrip.member.exception.MemberException;
import com.ssafy.enjoytrip.member.model.dto.MemberJoinDto;
import com.ssafy.enjoytrip.member.model.dto.MemberLoginDto;
import com.ssafy.enjoytrip.member.model.dto.MemberResponseDto;
import com.ssafy.enjoytrip.member.model.dto.MemberUpdateDto;
import com.ssafy.enjoytrip.member.model.dto.InformationFindRequestDto;
import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<String> memberJoin(@RequestBody MemberJoinDto memberJoinDto) throws Exception {
        log.info("Post - join");

        memberService.join(memberJoinDto);

        return ResponseEntity.ok()
                             .body("hello");
    }

    @PostMapping("/login")
    public ResponseEntity<String> memberLogin(@RequestBody MemberLoginDto memberLoginDto) {
        log.info("Post - login");
        String token = memberService.login(memberLoginDto);

        return ResponseEntity.ok()
                             .body(token);
    }

    @GetMapping("/test")
    public String apiTest(@AuthenticationPrincipal Member member) {

        return member.toString();
    }

    @GetMapping("/mypage")
    public ResponseEntity<MemberResponseDto> memberPage(@AuthenticationPrincipal Member member) {
        log.info("GET - mypage : {}", member.getLoginId());

        return ResponseEntity.ok()  
                             .body(MemberResponseDto.from(member));
    }
    @PutMapping("/mypage")
    public ResponseEntity<MemberResponseDto> memberInfoUpdate(@RequestBody MemberUpdateDto memberUpdateDto, @AuthenticationPrincipal Member member) throws Exception {
        log.info("PUT - mapage : {}", member.getLoginId());

        if (!memberUpdateDto.getLoginId()
                            .equals(member.getLoginId())) {
            throw new MemberException(ErrorCode.UNAUTHORIZED, "권한이 없습니다.");
        }

        Member updatedMember = memberService.update(memberUpdateDto);

        return ResponseEntity.ok()
                             .body(MemberResponseDto.from(updatedMember));
    }

    @DeleteMapping("/signout")
    public ResponseEntity<String> memberSignOut(@AuthenticationPrincipal Member member) {
        log.info("DELETE - signout : {}", member.getLoginId());
        memberService.signOut(member.getLoginId());

        return ResponseEntity.ok().body("탈퇴");
    }

    @PostMapping("/find/password")
    public ResponseEntity<String> findPassword(InformationFindRequestDto informationFindRequestDto) {

        String msg = memberService.findPassword(informationFindRequestDto);

        return ResponseEntity.ok().body(msg);
    }

    @PostMapping("/find/id")
    public ResponseEntity<String> findLoginId(InformationFindRequestDto informationFindRequestDto) {

        String msg = memberService.findLoginId(informationFindRequestDto);

        return ResponseEntity.ok().body(msg);
    }

}
