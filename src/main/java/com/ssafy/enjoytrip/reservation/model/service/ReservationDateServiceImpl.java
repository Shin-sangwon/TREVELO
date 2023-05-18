package com.ssafy.enjoytrip.reservation.model.service;

import com.ssafy.enjoytrip.global.ErrorCode;
import com.ssafy.enjoytrip.reservation.exception.ReservationException;
import com.ssafy.enjoytrip.reservation.model.dto.ReservationDateCheckDto;
import com.ssafy.enjoytrip.reservation.model.dto.request.ReservationDateSaveRequestDto;
import com.ssafy.enjoytrip.reservation.model.dto.request.ReservationSaveRequestDto;
import com.ssafy.enjoytrip.reservation.model.mapper.ReservationDateMapper;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

        if (cnt > 0) {
            throw new ReservationException(ErrorCode.ROOM_ALREADY_RESERVED,
                ErrorCode.ROOM_ALREADY_RESERVED.getMessage());
        }
    }

    @Override
    public Long getDaysBetween(LocalDate checkInDate, LocalDate checkOutDate) {
        return ChronoUnit.DAYS.between(checkInDate, checkOutDate) + 1;
    }

    @Transactional
    @Override
    public void save(ReservationSaveRequestDto reservationSaveRequestDto) {
        LocalDate startDate = reservationSaveRequestDto.getCheckInDate();
        LocalDate endDate = reservationSaveRequestDto.getCheckOutDate();

        while (!startDate.isAfter(endDate)) {
            reservationDateMapper.save(ReservationDateSaveRequestDto.of(
                reservationSaveRequestDto.getRoomId(), reservationSaveRequestDto.getId(),
                startDate));

            startDate = startDate.plusDays(1L);
        }
    }

    @Transactional
    @Override
    public void delete(Long reservationId) {

        reservationDateMapper.delete(reservationId);
    }

    @Override
    public void checkCancelable(LocalDate checkInDate) {
        LocalDate today = LocalDate.now();
        long daysDiff = ChronoUnit.DAYS.between(today, checkInDate);

        if (daysDiff <= 1) {
            throw new ReservationException(ErrorCode.CANCELLATION_EXPIRED,
                                           ErrorCode.CANCELLATION_EXPIRED.getMessage());
        }
    }

}
