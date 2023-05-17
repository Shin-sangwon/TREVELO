package com.ssafy.enjoytrip.reservation.controller;

import com.ssafy.enjoytrip.global.ErrorCode;
import com.ssafy.enjoytrip.member.exception.MemberException;
import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.reservation.model.dto.request.ReservationSaveRequestDto;
import com.ssafy.enjoytrip.reservation.model.dto.response.ReservationResponseDto;
import com.ssafy.enjoytrip.reservation.model.entity.Reservation;
import com.ssafy.enjoytrip.reservation.model.service.ReservationDateService;
import com.ssafy.enjoytrip.reservation.model.service.ReservationService;
import com.ssafy.enjoytrip.room.model.dto.response.RoomResponseDto;
import com.ssafy.enjoytrip.room.model.service.RoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/reservation")
@RestController
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationDateService reservationDateService;
    private final RoomService roomService;

    @GetMapping("/")
    public ResponseEntity<List<ReservationResponseDto>> showReservationList(@AuthenticationPrincipal Member member) {
        log.info("'{}' member call GET - showReservationList", member.getLoginId());

        List<ReservationResponseDto> reservationList = reservationService.findAllByMemberId(member.getId());

        return ResponseEntity.ok().body(reservationList);
    }

    /*
    1. 기존에 예약 된 방이 있는지 검사하기(room_reservation_date)(트랜잭션)
    1-1. 있다면, custom error 던지기
    2. 예약금 (10%)가 있는지 검사하기
    2-1. 없다면, custom error 던지기
    3. 예약 내역 저장해주기(dto -> memberid, totalPrice)
    4. room_reservation_date에 사이 날짜 전부 추가해주기(트랜잭션)
    5. 예약 완료되었습니다 .. !!
     */
    @PostMapping("/{roomId}")
    public ResponseEntity<ReservationResponseDto> makeReservation(@PathVariable("roomId") Long roomId, @AuthenticationPrincipal Member member, @RequestBody ReservationSaveRequestDto reservationSaveRequestDto) {
        log.info("'{}' member call POST - makeReservation", member.getLoginId());

        reservationDateService.isRoomAvailable(roomId, reservationSaveRequestDto.getCheckInDate(), reservationSaveRequestDto.getCheckOutDate());
        RoomResponseDto room = roomService.findById(roomId);

        long daysBetween = reservationDateService.getDaysBetween(reservationSaveRequestDto.getCheckInDate(), reservationSaveRequestDto.getCheckOutDate());
        long totalPrice = daysBetween * room.getPricePerNight();

        reservationService.checkSufficientMileage(totalPrice, member.getMileage());
        reservationService.mapReservationDetails(reservationSaveRequestDto, member.getId(), room.getId(), totalPrice);

        reservationService.save(reservationSaveRequestDto);
        reservationDateService.save(reservationSaveRequestDto);

        return ResponseEntity.ok().body(reservationService.findById(reservationSaveRequestDto.getId()));
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<String> cancelReservation(@PathVariable("reservationId") Long reservationId, @AuthenticationPrincipal Member member) {

        Reservation reservation = Reservation.from(reservationService.findById(reservationId));

        if(member.getId() != reservation.getCustomerId()) {
            throw new MemberException(ErrorCode.UNAUTHORIZED, ErrorCode.UNAUTHORIZED.getMessage());
        }

        reservationDateService.delete(reservation.getId());
        reservationService.delete(reservation.getId());

        return ResponseEntity.ok().body("예약이 취소되었습니다.");
    }

}
