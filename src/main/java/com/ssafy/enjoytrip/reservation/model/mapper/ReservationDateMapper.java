package com.ssafy.enjoytrip.reservation.model.mapper;

import com.ssafy.enjoytrip.reservation.model.dto.ReservationDateCheckDto;
import com.ssafy.enjoytrip.reservation.model.dto.request.ReservationDateSaveRequestDto;
import com.ssafy.enjoytrip.reservation.model.entity.ReservationDate;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationDateMapper {

    int reservationAvailableCheck(ReservationDateCheckDto reservationDateCheckDto);

    void save(ReservationDateSaveRequestDto reservationDateSaveRequestDto);

    void delete(Long reservationId);

    List<ReservationDate> findAllDateByRoomIdAfterToday(Long roomId);
}
