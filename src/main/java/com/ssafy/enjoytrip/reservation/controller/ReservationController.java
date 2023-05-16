package com.ssafy.enjoytrip.reservation.controller;

import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.reservation.model.dto.ReservationResponseDto;
import com.ssafy.enjoytrip.reservation.model.service.ReservationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/reservation")
@RestController
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/")
    public ResponseEntity<List<ReservationResponseDto>> showReservationList(@AuthenticationPrincipal Member member) {
        log.info("'{}' member call GET - showReservationList", member.getLoginId());

        List<ReservationResponseDto> reservationList = reservationService.findAllByMemberId(member.getId());

        return ResponseEntity.ok().body(reservationList);
    }

}
