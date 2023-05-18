package com.ssafy.enjoytrip.room.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ssafy.enjoytrip.common.ServiceTest;
import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.room.exception.RoomException;
import com.ssafy.enjoytrip.room.model.dto.request.RoomCreateRequestDto;
import com.ssafy.enjoytrip.room.model.dto.request.RoomUpdateRequestDto;
import com.ssafy.enjoytrip.room.model.dto.response.RoomListResponseDto;
import com.ssafy.enjoytrip.room.model.dto.response.RoomResponseDto;
import com.ssafy.enjoytrip.roompicture.model.service.RoomPictureService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@SpringBootTest
class RoomServiceTest extends ServiceTest {

    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomPictureService roomPictureService;

    @Test
    @Transactional
    @DisplayName("숙소_전체조회_성공한다")
    public void 숙소_전체조회_성공한다() {

        Member member1 = memberService.findByLoginId("test1");
        Member member2 = memberService.findByLoginId("test2");

        RoomCreateRequestDto room1 = RoomCreateRequestDto.builder()
                                                         .roomName("테스트방1")
                                                         .address("서울특별시")
                                                         .introduce("아늑합니다")
                                                         .pricePerNight(100000L)
                                                         .sidoCode(5)
                                                         .build();

        RoomCreateRequestDto room2 = RoomCreateRequestDto.builder()
                                                         .roomName("테스트방2")
                                                         .address("광주광역시")
                                                         .introduce("아늑합니다")
                                                         .pricePerNight(200000L)
                                                         .sidoCode(5)
                                                         .build();
        roomService.save(room1, member1);
        roomService.save(room2, member2);

        List<RoomListResponseDto> roomList = roomService.findAll();

        assertEquals(roomList.size(), 2);
    }

    @Test
    @Transactional
    @DisplayName("숙소_개별조회_성공한다")
    public void 숙소_개별조회_성공한다() {

        Member member1 = memberService.findByLoginId("test1");
        Member member2 = memberService.findByLoginId("test2");

        RoomCreateRequestDto room1 = RoomCreateRequestDto.builder()
                                                         .roomName("테스트방1")
                                                         .address("서울특별시")
                                                         .introduce("아늑합니다")
                                                         .pricePerNight(100000L)
                                                         .sidoCode(5)
                                                         .build();

        roomService.save(room1, member1);

        RoomResponseDto room = roomService.findById(1L);

        assertEquals(room.getRoomName(), "테스트방1");
        assertEquals(room.getOwnerId(), 2);
    }

    @Test
    @Transactional
    @DisplayName("존재하지_않는_숙소를_조회하면_RoomException_던진다")
    public void 존재하지_않는_숙소를_조회하면_RoomException_던진다() {

        assertThrows(RoomException.class, () -> roomService.findById(9999L));
    }

    @Test
    @Transactional
    @DisplayName("숙소_저장_성공한다")
    public void 숙소_저장_성공한다() {

        Member member1 = memberService.findByLoginId("test1");

        RoomCreateRequestDto room1 = RoomCreateRequestDto.builder()
                                                         .roomName("테스트방1")
                                                         .address("서울특별시")
                                                         .introduce("아늑합니다")
                                                         .pricePerNight(100000L)
                                                         .sidoCode(5)
                                                         .build();

        roomService.save(room1, member1);

        RoomResponseDto room = roomService.findById(1L);

        assertEquals(room.getRoomName(), "테스트방1");
        assertEquals(room.getAddress(), "서울특별시");
        assertEquals(room.getOwnerId(), member1.getId());
    }

    @Test
    @Transactional
    @DisplayName("숙소_정보_수정_성공한다")
    public void 숙소_정보_수정_성공한다() {

        Member member1 = memberService.findByLoginId("test1");

        RoomCreateRequestDto room1 = RoomCreateRequestDto.builder()
                                                         .roomName("테스트방1")
                                                         .address("서울특별시")
                                                         .introduce("아늑합니다")
                                                         .pricePerNight(100000L)
                                                         .sidoCode(5)
                                                         .build();

        roomService.save(room1, member1);

        RoomUpdateRequestDto roomUpdateRequestDto = RoomUpdateRequestDto.builder()
                                                                        .id(1L)
                                                                        .roomName("수정_테스트방")
                                                                        .address("경기도")
                                                                        .build();

        roomService.update(roomUpdateRequestDto);

        RoomResponseDto room = roomService.findById(1L);

        assertEquals(room.getRoomName(), "수정_테스트방");
        assertEquals(room.getAddress(), "경기도");

    }

    @Test
    @Transactional
    @DisplayName("숙소_삭제_성공한다")
    public void 숙소_삭제_성공한다() {

        Member member1 = memberService.findByLoginId("test1");

        RoomCreateRequestDto room1 = RoomCreateRequestDto.builder()
                                                         .roomName("테스트방1")
                                                         .address("서울특별시")
                                                         .introduce("아늑합니다")
                                                         .pricePerNight(100000L)
                                                         .sidoCode(5)
                                                         .build();

        roomService.save(room1, member1);

        roomService.delete(1L);

        List<RoomListResponseDto> roomList = roomService.findAll();

        assertEquals(roomList.size(), 0);

    }
}