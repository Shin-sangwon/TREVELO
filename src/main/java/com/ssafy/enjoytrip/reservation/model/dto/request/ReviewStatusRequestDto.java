package com.ssafy.enjoytrip.reservation.model.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewStatusRequestDto {

    private Long customerId;
    private Long roomId;

}
