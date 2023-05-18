package com.ssafy.enjoytrip.reservation.model.service;

import com.ssafy.enjoytrip.reservation.model.dto.request.ReservationSaveRequestDto;
import com.ssafy.enjoytrip.reservation.model.dto.response.ReservationResponseDto;
import java.util.List;

public interface ReservationService {

    List<ReservationResponseDto> findAllByMemberId(Long id);

    ReservationResponseDto findById(Long id);

    void checkSufficientMileage(long price, Long mileage);

    void save(ReservationSaveRequestDto reservationSaveRequestDto);

    void delete(Long id);

    void mapReservationDetails(ReservationSaveRequestDto reservationSaveRequestDto, Long memberId, Long roomId, long totalPrice);
}
