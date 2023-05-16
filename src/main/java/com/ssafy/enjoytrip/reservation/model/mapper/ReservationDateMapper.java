package com.ssafy.enjoytrip.reservation.model.mapper;

import com.ssafy.enjoytrip.reservation.model.dto.ReservationDateCheckDto;
import com.ssafy.enjoytrip.reservation.model.dto.request.ReservationDateSaveRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationDateMapper {

    int reservationAvailableCheck(ReservationDateCheckDto reservationDateCheckDto);

    void save(ReservationDateSaveRequestDto reservationDateSaveRequestDto);
}
