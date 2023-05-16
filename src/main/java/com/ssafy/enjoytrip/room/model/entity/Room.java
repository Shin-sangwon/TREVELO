package com.ssafy.enjoytrip.room.model.entity;


import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Alias("room")
public class Room {

    private Long id;
    private Long ownerId;
    private String roomName;
    private String address;
    private String introduce;
    private Long pricePerNight;
    private LocalDateTime createdat;
    private LocalDateTime updatedat;
    private int sidoCode;
    private int gugunCode;
    
}
