package com.ssafy.enjoytrip.room.model.dto.response;

import com.ssafy.enjoytrip.room.model.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class RoomListResponseDto {

    private Long id;
    private String roomName;
    private String address;
    private Long pricePerNight;

    public RoomListResponseDto(Room entity) {
        this.id = entity.getId();
        this.roomName = entity.getRoomName();
        this.address = entity.getAddress();
        this.pricePerNight = entity.getPricePerNight();
    }

}
