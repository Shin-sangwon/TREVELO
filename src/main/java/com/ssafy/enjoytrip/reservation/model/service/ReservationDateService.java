package com.ssafy.enjoytrip.reservation.model.service;

import com.ssafy.enjoytrip.reservation.model.dto.request.ReservationSaveRequestDto;
import com.ssafy.enjoytrip.reservation.model.dto.response.ReservationDateResponseDto;
import java.time.LocalDate;
import java.util.List;

public interface ReservationDateService {

    void isRoomAvailable(Long roomId, LocalDate checkInDate, LocalDate checkOutDate);

    Long getDaysBetween(LocalDate checkInDate, LocalDate checkOutDate);

    void save(ReservationSaveRequestDto reservationSaveRequestDto);

    void delete(Long reservationId);

    void checkCancelable(LocalDate checkInDate);

    List<ReservationDateResponseDto> findAllDateByRoomIdAfterToday(Long roomId);
}
