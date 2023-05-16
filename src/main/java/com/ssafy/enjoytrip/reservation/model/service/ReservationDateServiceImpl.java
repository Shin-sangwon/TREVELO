package com.ssafy.enjoytrip.reservation.model.service;

import com.ssafy.enjoytrip.global.ErrorCode;
import com.ssafy.enjoytrip.reservation.exception.ReservationException;
import com.ssafy.enjoytrip.reservation.model.dto.ReservationDateCheckDto;
import com.ssafy.enjoytrip.reservation.model.mapper.ReservationDateMapper;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationDateServiceImpl implements ReservationDateService {

    private final ReservationDateMapper reservationDateMapper;

    @Transactional(readOnly = true)
    @Override
    public void isRoomAvailable(Long roomId, LocalDate checkInDate, LocalDate checkOutDate) {

        int cnt = reservationDateMapper.reservationAvailableCheck(
            ReservationDateCheckDto.builder()
                                   .roomId(roomId)
                                   .checkInDate(checkInDate)
                                   .checkOutDate(checkOutDate)
                                   .build());

        if(cnt > 0) {
            throw new ReservationException(ErrorCode.ROOM_ALREADY_RESERVED, ErrorCode.ROOM_ALREADY_RESERVED.getMessage());
        }
    }
}
