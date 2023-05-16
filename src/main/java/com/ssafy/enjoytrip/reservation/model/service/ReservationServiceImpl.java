package com.ssafy.enjoytrip.reservation.model.service;

import com.ssafy.enjoytrip.global.ErrorCode;
import com.ssafy.enjoytrip.reservation.exception.ReservationException;
import com.ssafy.enjoytrip.reservation.model.dto.request.ReservationSaveRequestDto;
import com.ssafy.enjoytrip.reservation.model.dto.response.ReservationResponseDto;
import com.ssafy.enjoytrip.reservation.model.entity.Reservation;
import com.ssafy.enjoytrip.reservation.model.mapper.ReservationMapper;
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

        Reservation reservation =  reservationMapper.findById(id)
                                                    .orElseThrow(() -> new ReservationException(
                                                        ErrorCode.RESERVATION_NOT_FOUND,
                                                        ErrorCode.RESERVATION_NOT_FOUND.getMessage()));

        return ReservationResponseDto.from(reservation);
    }

    @Override
    public void checkSufficientMileage(long price, Long mileage) {
        log.info(String.valueOf(price / 10.0));
        if (price / 10.0 > mileage) {
            throw new ReservationException(ErrorCode.INSUFFICIENT_MILEAGE,
                                           ErrorCode.INSUFFICIENT_MILEAGE.getMessage());
        }
    }

    @Transactional
    @Override
    public void save(ReservationSaveRequestDto reservationSaveRequestDto) {

        reservationMapper.save(reservationSaveRequestDto);
    }
}
