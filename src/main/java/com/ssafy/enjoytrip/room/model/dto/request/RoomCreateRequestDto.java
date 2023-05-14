package com.ssafy.enjoytrip.room.model.dto.request;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RoomCreateRequestDto {

    private Long id;
    private Long ownerId;
    private String roomName;
    private String address;
    private String introduce;
    private Long pricePerNight;
    private LocalDateTime createdat;
    private LocalDateTime updatedat;
    private int sidoCode;
    //private int gugunCode;

    public void mapOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
