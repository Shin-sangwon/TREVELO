package com.ssafy.enjoytrip.room.model.dto.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RoomCreateRequestDto {

    private Long ownerId;
    private String roomName;
    private String address;
    private String introduce;
    private Long pricePerNight;
    private LocalDateTime createdat;
    private LocalDateTime updatedat;
    private int sidoCode;
    //private int gugunCode;
}
