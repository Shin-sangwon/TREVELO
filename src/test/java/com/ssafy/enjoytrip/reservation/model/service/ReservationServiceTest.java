package com.ssafy.enjoytrip.reservation.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.ssafy.enjoytrip.common.ServiceTest;
import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.reservation.model.dto.request.ReservationSaveRequestDto;
import com.ssafy.enjoytrip.reservation.model.dto.response.ReservationResponseDto;
import com.ssafy.enjoytrip.reservation.model.entity.Reservation;
import com.ssafy.enjoytrip.room.model.dto.request.RoomCreateRequestDto;
import com.ssafy.enjoytrip.transaction.model.dto.response.TransactionResponseDto;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

class ReservationServiceTest extends ServiceTest {

    @BeforeEach
    void tearUp() {
        Member member1 = memberService.findByLoginId("test1");
        Member member2 = memberService.findByLoginId("test2");
        // 숙소 생성
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

    }

    @Test
    @Transactional
    @DisplayName("예약을_저장하면_거래내역_저장되고_마일리지_변경된다")
    void 예약을_저장하면_거래내역_저장되고_마일리지_변경된다() {

        ReservationSaveRequestDto reservationSaveRequestDto =
            ReservationSaveRequestDto.builder()
                                     .customerId(2L)
                                     .roomId(1L)
                                     .isPaid(false)
                                     .totalPrice(1000000L)
                                     .checkInDate(LocalDate.now())
                                     .checkOutDate(LocalDate.now())
                                     .build();

        reservationService.save(reservationSaveRequestDto);

        Reservation reservation = Reservation.from(reservationService.findById(1L));
        List<TransactionResponseDto> transaction = transactionService.findAllByMemberId(2L);
        Member member = memberService.findByLoginId("test2");

        assertNotNull(reservation);
        assertNotNull(transaction.get(0));
        assertNotEquals(member.getMileage(), 10000000);

    }

    @Test
    @DisplayName("회원별_모든_예약내역_조회된다")
    void 회원별_모든_예약내역_조회된다() {

        ReservationSaveRequestDto reservationSaveRequestDto1 =
            ReservationSaveRequestDto.builder()
                                     .customerId(2L)
                                     .roomId(1L)
                                     .isPaid(false)
                                     .totalPrice(1000000L)
                                     .checkInDate(LocalDate.now())
                                     .checkOutDate(LocalDate.now())
                                     .build();

        ReservationSaveRequestDto reservationSaveRequestDto2 =
            ReservationSaveRequestDto.builder()
                                     .customerId(2L)
                                     .roomId(1L)
                                     .isPaid(false)
                                     .totalPrice(1000000L)
                                     .checkInDate(LocalDate.now().plusDays(1L))
                                     .checkOutDate(LocalDate.now().plusDays(1L))
                                     .build();

        reservationService.save(reservationSaveRequestDto1);
        reservationService.save(reservationSaveRequestDto2);

        List<ReservationResponseDto> reservationList = reservationService.findAllByMemberId(2L);

        assertEquals(2, reservationList.size());
        assertEquals(1, reservationList.get(0).getRoomId());
        assertEquals(2, reservationList.get(1).getCustomerId());



    }
}