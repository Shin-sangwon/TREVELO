package com.ssafy.enjoytrip.reservation.model.dto.response;

import com.ssafy.enjoytrip.reservation.model.entity.Reservation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationResponseDto {

    private Long id;
    private Long customerId;
    private Long roomId;
    private String roomName;
    private Long totalPrice;
    private boolean isPaid;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime createdat;
    private LocalDateTime updatedat;

    public static ReservationResponseDto from(Reservation entity) {
        return ReservationResponseDto.builder()
                                     .id(entity.getId())
                                     .customerId(entity.getCustomerId())
                                     .roomId(entity.getRoomId())
                                     .totalPrice(entity.getTotalPrice())
                                     .isPaid(entity.isPaid())
                                     .roomName(entity.getRoomName())
                                     .checkInDate(entity.getCheckInDate())
                                     .checkOutDate(entity.getCheckOutDate())
                                     .createdat(entity.getCreatedat())
                                     .updatedat(entity.getUpdatedat())
                                     .build();
    }

}
