package com.ssafy.enjoytrip.reservation.model.service;

import com.ssafy.enjoytrip.global.ErrorCode;
import com.ssafy.enjoytrip.reservation.model.dto.response.ReservationResponseDto;
import com.ssafy.enjoytrip.reservation.model.mapper.ReservationMapper;
import com.ssafy.enjoytrip.room.exception.RoomException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationMapper reservationMapper;

    @Transactional(readOnly = true)
    @Override
    public List<ReservationResponseDto> findAllByMemberId(Long id) {

        return reservationMapper.findAllByMemberId(id);
    }

    @Transactional(readOnly = true)
    @Override
    public ReservationResponseDto findById(Long id) {
        return reservationMapper.findById(id)
                                .orElseThrow(() -> new RoomException(
                                    ErrorCode.RESERVATION_NOT_FOUND,
                                    ErrorCode.RESERVATION_NOT_FOUND.getMessage()));
    }
}
