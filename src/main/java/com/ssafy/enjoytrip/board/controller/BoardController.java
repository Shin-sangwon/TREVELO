package com.ssafy.enjoytrip.board.controller;

import com.ssafy.enjoytrip.board.model.dto.BoardDto;
import com.ssafy.enjoytrip.board.model.service.BoardService;
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
@RequestMapping("/api/v1/board")
@RestController
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    @Autowired
    public BoardController(BoardService boardService, MemberService memberService) {
        this.boardService = boardService;
        this.memberService = memberService;
    }

    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody BoardDto boardDto, @AuthenticationPrincipal Member member) throws Exception {
        log.info(memberService.findByLoginId(member.getLoginId()).toString());

        boardDto.setMemberId(memberService.findByLoginId(member.getLoginId()).getId());
        boardService.write(boardDto);

        log.info(boardDto.toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<BoardDto>> list(@AuthenticationPrincipal Member member) throws SQLException {
        List<BoardDto> list = boardService.getlist();

        log.info(list.toString());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/view/{boardId}")
    public ResponseEntity<BoardDto> view(@AuthenticationPrincipal Member member, @PathVariable("boardId") long boardId) throws SQLException {
        BoardDto boardDto = boardService.view(boardId);

        log.info(boardDto.toString());

        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{boardId}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal Member member, @PathVariable("boardId") long boardId) throws SQLException {
        boardService.delete(boardId);

        List<BoardDto> list = boardService.getlist();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/modify")
    public ResponseEntity<?> modify(@AuthenticationPrincipal Member member,@RequestBody BoardDto boardDto) throws Exception {
        boardDto.setMemberId(memberService.findByLoginId(member.getLoginId()).getId());

        boardService.update(boardDto);

        List<BoardDto> list = boardService.getlist();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }



}
