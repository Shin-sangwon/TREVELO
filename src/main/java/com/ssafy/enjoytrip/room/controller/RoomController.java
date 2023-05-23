package com.ssafy.enjoytrip.room.controller;

import com.ssafy.enjoytrip.global.exception.ErrorCode;
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
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity<List<RoomListResponseDto>> showRoomList(@AuthenticationPrincipal Member member) {
        log.info("RoomList - GET");

        return ResponseEntity.ok().body(roomService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDto> showRoom(@PathVariable("id") Long id, @AuthenticationPrincipal Member member) {
        log.info("RoomDetail - GET");

        return ResponseEntity.ok().body(roomService.findById(id));
    }

    // TODO : 트랜잭션 범위 고민해보기
    @PostMapping("/")
    public ResponseEntity<RoomResponseDto> writeRoom(@AuthenticationPrincipal Member member, @RequestPart("roomCreateRequestDto") RoomCreateRequestDto roomCreateRequestDto, @RequestPart("imageList") List<MultipartFile> imageList) {
        log.info("RoomCreate - POST");

        if(member.getRole().getAuthLevel() <= Role.SELLER.getAuthLevel()) {
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

        if(!Objects.equals(savedOwnerId, member.getId())) {
            throw new RoomException(ErrorCode.ROOM_PERMISSION_DENIED, ErrorCode.ROOM_PERMISSION_DENIED.getMessage());
        }
        /*
        1. 게시물 업데이트
        2. AWS S3 Bucket에 있는 사진을 모두 지운다
        3. AWS S3에 사진을 다시 등록한다.
         */
        roomUpdateRequestDto.mapRoomId(id);
        roomService.update(roomUpdateRequestDto);
        roomPictureService.deleteAllWithS3(id);

        if(imageList != null) {
            List<String> imageUrl = amazonS3Service.uploadFiles(imageList);
            roomPictureService.saveAll(imageUrl, id);
        }

        return ResponseEntity.ok().body(roomService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") Long id, @AuthenticationPrincipal Member member) {

        log.info("DELETE - deleteRoom : {}번 Room 삭제 요청", id);

        Long savedOwnerId = roomService.findById(id).getOwnerId();

        if(!Objects.equals(savedOwnerId, member.getId())) {
            throw new RoomException(ErrorCode.ROOM_PERMISSION_DENIED, ErrorCode.ROOM_PERMISSION_DENIED.getMessage());
        }

        roomPictureService.deleteAll(id);
        roomService.delete(id);


        return ResponseEntity.ok().body("숙소가 정상 삭제되었습니다.");

    }
}
