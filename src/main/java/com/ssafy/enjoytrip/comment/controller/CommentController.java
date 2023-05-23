package com.ssafy.enjoytrip.comment.controller;

import com.ssafy.enjoytrip.comment.model.dto.CommentDto;
import com.ssafy.enjoytrip.comment.model.service.CommentService;
import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.member.model.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@RequestMapping("/api/v1/comment")
@RestController
public class CommentController {

    private final CommentService commentService;
    private final MemberService memberService;

    @Autowired
    public CommentController(CommentService commentService, MemberService memberService) {
        this.commentService = commentService;
        this.memberService = memberService;
    }

    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody CommentDto commentDto, @AuthenticationPrincipal Member member) throws SQLException{
        commentDto.setMemberId(memberService.findByLoginId(member.getLoginId()).getId());

        commentService.write(commentDto);

        log.info(commentDto.toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal Member member, @PathVariable("commentId") long commentId) throws SQLException{

        commentService.delete(commentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getlist/{boardId}")
    public ResponseEntity<List<CommentDto>> getlist(@AuthenticationPrincipal Member member, @PathVariable("boardId") long boardId) throws SQLException{

        List<CommentDto> list = commentService.getlist(boardId);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@AuthenticationPrincipal Member member, @RequestBody CommentDto commentDto) throws SQLException{

        commentService.update(commentDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
