package com.ssafy.enjoytrip.room.controller;

import com.ssafy.enjoytrip.global.ErrorCode;
import com.ssafy.enjoytrip.global.service.AmazonS3Service;
import com.ssafy.enjoytrip.member.exception.MemberException;
import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.member.model.entity.Role;
import com.ssafy.enjoytrip.room.exception.RoomException;
import com.ssafy.enjoytrip.room.model.dto.request.RoomCreateRequestDto;
import com.ssafy.enjoytrip.room.model.dto.request.RoomUpdateRequestDto;
import com.ssafy.enjoytrip.room.model.dto.response.RoomListResponseDto;
import com.ssafy.enjoytrip.room.model.dto.response.RoomResponseDto;
import com.ssafy.enjoytrip.room.model.service.RoomService;
import com.ssafy.enjoytrip.roompicture.model.service.RoomPictureService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/room")
@RestController
public class RoomController {

    private final RoomService roomService;
    private final RoomPictureService roomPictureService;
    private final AmazonS3Service amazonS3Service;

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

    // TODO : 트랜잭션 범위 고민해보기
    @PostMapping("/write")
    public ResponseEntity<RoomResponseDto> writeRoom(@AuthenticationPrincipal Member member, @RequestPart RoomCreateRequestDto roomCreateRequestDto, @RequestPart List<MultipartFile> imageList) {
        log.info("RoomCreate - POST");

        if(member.getRole() == Role.MEMBER) {
            throw new MemberException(ErrorCode.UNAUTHORIZED, ErrorCode.UNAUTHORIZED.getMessage());
        }
        // 숙소 저장
        roomService.save(roomCreateRequestDto, member);

        Long roomId = roomCreateRequestDto.getId();

        log.info("생성된 room pk = '{}'", roomId);

        if(imageList != null) {
            List<String> imageUrl = amazonS3Service.uploadFiles(imageList);
            roomPictureService.saveAll(imageUrl, roomId);
        }

        return ResponseEntity.ok().body(roomService.findById(roomCreateRequestDto.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDto> updateRoom(@PathVariable("id") Long id, @AuthenticationPrincipal Member member, @RequestPart RoomUpdateRequestDto roomUpdateRequestDto, @RequestPart List<MultipartFile> imageList) {
        log.info("PUT - updateRoom : {}번 Room 수정 요청", id);

        Long savedOwnerId = roomService.findById(id).getOwnerId();

        if(member.getRole() == Role.ADMIN || !Objects.equals(savedOwnerId, member.getId())) {
            throw new RoomException(ErrorCode.ROOM_PERMISSION_DENIED, ErrorCode.ROOM_PERMISSION_DENIED.getMessage());
        }

        roomService.update(roomUpdateRequestDto);
        roomPictureService.update(id, imageList);

        return ResponseEntity.ok().body(roomService.findById(id));
    }
}
