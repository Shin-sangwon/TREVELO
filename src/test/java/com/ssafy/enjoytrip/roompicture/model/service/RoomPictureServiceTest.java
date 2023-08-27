package com.ssafy.enjoytrip.roompicture.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ssafy.enjoytrip.common.ServiceTest;
import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.room.model.dto.request.RoomCreateRequestDto;
import com.ssafy.enjoytrip.roompicture.model.entity.RoomPicture;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

class RoomPictureServiceTest extends ServiceTest {

    @BeforeEach
    void tearUp() {

        Member member1 = memberService.findByLoginId("test1");
        Member member2 = memberService.findByLoginId("test2");

        RoomCreateRequestDto room1 = RoomCreateRequestDto.builder()
                                                         .roomName("테스트방1")
                                                         .address("서울특별시")
                                                         .introduce("아늑합니다")
                                                         .pricePerNight(100000L)
                                                         .build();

        RoomCreateRequestDto room2 = RoomCreateRequestDto.builder()
                                                         .roomName("테스트방2")
                                                         .address("광주광역시")
                                                         .introduce("아늑합니다")
                                                         .pricePerNight(200000L)
                                                         .build();
        roomService.save(room1, member1);
        roomService.save(room2, member2);
    }

    @Test
    @Transactional
    @DisplayName("숙소에_저장된_사진_조회_성공한다")
    void 숙소에_저장된_사진_조회_성공한다() {

        List<String> awsUrlList = Arrays.asList("a", "b", "c");
        //1번방에 url 저장해주기
        roomPictureService.saveAll(awsUrlList, 1L);

        List<RoomPicture> roomPictureList = roomPictureService.getRoomPicture(1L);

        assertEquals(roomPictureList.size(), 3);
    }

    @Test
    @Transactional
    @DisplayName("이미지_url_모두_등록된다")
    void 이미지_url_모두_등록된다() {

        List<String> awsUrlList = Arrays.asList("image1", "image2", "image3");
        //1번방에 url 저장해주기
        roomPictureService.saveAll(awsUrlList, 1L);

        List<RoomPicture> roomPictureList = roomPictureService.getRoomPicture(1L);

        assertEquals(roomPictureList.size(), 3);
        assertEquals(roomPictureList.get(0).getPicture(), "image1");
        assertEquals(roomPictureList.get(1).getPicture(), "image2");
        assertEquals(roomPictureList.get(2).getPicture(), "image3");

    }

    @Test
    @Transactional
    @DisplayName("이미지_url_모두_삭제된다")
    void 이미지_url_모두_삭제된다() {

        List<String> awsUrlList = Arrays.asList("image1", "image2", "image3");
        //1번방에 url 저장해주기
        roomPictureService.saveAll(awsUrlList, 1L);

        roomPictureService.deleteAll(1L);

        List<RoomPicture> roomPictureList = roomPictureService.getRoomPicture(1L);

        assertEquals(roomPictureList.size(), 0);

    }


}