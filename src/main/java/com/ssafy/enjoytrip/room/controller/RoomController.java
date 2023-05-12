package com.ssafy.enjoytrip.room.controller;

import com.ssafy.enjoytrip.global.ErrorCode;
import com.ssafy.enjoytrip.member.exception.MemberException;
import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.member.model.entity.Role;
import com.ssafy.enjoytrip.room.model.dto.request.RoomCreateRequestDto;
import com.ssafy.enjoytrip.room.model.dto.response.RoomListResponseDto;
import com.ssafy.enjoytrip.room.model.dto.response.RoomResponseDto;
import com.ssafy.enjoytrip.room.model.service.RoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/room")
@RestController
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/")
    public ResponseEntity<List<RoomListResponseDto>> showRoomList(@AuthenticationPrincipal Member member) throws Exception{
        log.info("RoomList - GET");

        return ResponseEntity.ok().body(roomService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDto> showRoom(@PathVariable("id") Long id, @AuthenticationPrincipal Member member) {
        log.info("RoomDetail - GET");

        return ResponseEntity.ok().body(roomService.findById(id));
    }

    @PostMapping("/write")
    public ResponseEntity<String> writeRoom(@AuthenticationPrincipal Member member, @RequestBody
        RoomCreateRequestDto roomCreateRequestDto) {
        log.info("RoomCreate - POST");

        if(member.getRole() == Role.MEMBER) {
            throw new MemberException(ErrorCode.UNAUTHORIZED, ErrorCode.UNAUTHORIZED.getMessage());
        }


        return ResponseEntity.ok("hello");
    }
}
