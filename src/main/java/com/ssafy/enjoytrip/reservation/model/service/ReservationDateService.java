package com.ssafy.enjoytrip.reservation.model.service;

import java.time.LocalDate;

public interface ReservationDateService {

    void isRoomAvailable(Long roomId, LocalDate checkInDate, LocalDate checkOutDate);
}
