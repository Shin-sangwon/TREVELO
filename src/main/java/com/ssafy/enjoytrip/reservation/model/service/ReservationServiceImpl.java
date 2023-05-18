package com.ssafy.enjoytrip.reservation.model.service;

import com.ssafy.enjoytrip.global.exception.ErrorCode;
import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.member.model.service.MemberService;
import com.ssafy.enjoytrip.reservation.exception.ReservationException;
import com.ssafy.enjoytrip.reservation.model.dto.request.ReservationSaveRequestDto;
import com.ssafy.enjoytrip.reservation.model.dto.response.ReservationResponseDto;
import com.ssafy.enjoytrip.reservation.model.entity.Reservation;
import com.ssafy.enjoytrip.reservation.model.mapper.ReservationMapper;
import com.ssafy.enjoytrip.transaction.model.entity.Transaction;
import com.ssafy.enjoytrip.transaction.model.service.TransactionService;
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
    private final TransactionService transactionService;
    private final MemberService memberService;

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

    @Override
    public Long getReservationMileage(Long mileage) {
        return null;
    }

    /*
    한 트랜잭션에 묶여야 하는 작업
     */
    @Transactional
    @Override
    public void save(ReservationSaveRequestDto reservationSaveRequestDto) {
        // 1. 예약 내역 저장하기
        reservationMapper.save(reservationSaveRequestDto);

        // 2. 거래 내역 트랜잭션 테이블에 저장하기
        transactionService.save(Transaction.from(reservationSaveRequestDto));

        // 3. 회원 마일리지 깎기
        memberService.deductMileage(Member.from(reservationSaveRequestDto));
    }

    @Transactional
    @Override
    public void delete(Long id) {

        reservationMapper.delete(id);
    }

    @Override
    public void mapReservationDetails(ReservationSaveRequestDto reservationSaveRequestDto, Long memberId, Long roomId, long totalPrice) {

        reservationSaveRequestDto.mapCustomerToReservation(memberId);
        reservationSaveRequestDto.mapRoomIdToReservation(roomId);
        reservationSaveRequestDto.mapTotalPriceToReservation(totalPrice);
    }
}
