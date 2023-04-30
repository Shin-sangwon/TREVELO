package com.ssafy.enjoytrip.member.controller;

import com.ssafy.enjoytrip.member.model.dto.MemberJoinDto;
import com.ssafy.enjoytrip.member.model.dto.MemberLoginDto;
import com.ssafy.enjoytrip.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<String> join(@RequestBody MemberJoinDto memberJoinDto) throws Exception{
        log.info("Post - join");

        memberService.join(memberJoinDto);

        return ResponseEntity.ok().body("hello");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberLoginDto memberLoginDto) throws Exception {
        log.info("Post - login");
        String token = memberService.login(memberLoginDto);

        return ResponseEntity.ok().body(token);
    }

    @GetMapping("/test")
    public String apiTest() {

        return "hello world!";
    }
}
