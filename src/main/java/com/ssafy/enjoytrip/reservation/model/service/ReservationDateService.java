package com.ssafy.enjoytrip.reservation.model.service;

import com.ssafy.enjoytrip.reservation.model.dto.request.ReservationSaveRequestDto;
import java.time.LocalDate;

public interface ReservationDateService {

    void isRoomAvailable(Long roomId, LocalDate checkInDate, LocalDate checkOutDate);

    Long getDaysBetween(LocalDate checkInDate, LocalDate checkOutDate);

    void save(ReservationSaveRequestDto reservationSaveRequestDto);
}
