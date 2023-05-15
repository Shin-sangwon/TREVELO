package com.ssafy.enjoytrip.room.model.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RoomUpdateRequestDto {

    private String id;
    private String roomName;
    private String address;
    private String introduce;
    private Long pricePerNight;
    private int sidoCode;

}
