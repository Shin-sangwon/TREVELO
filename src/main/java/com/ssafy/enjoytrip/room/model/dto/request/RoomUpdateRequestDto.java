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

    private Long id;
    private String roomName;
    private String address;
    private String introduce;
    private Long pricePerNight;

    public void mapRoomId(Long id) {
        this.id = id;
    }

}
