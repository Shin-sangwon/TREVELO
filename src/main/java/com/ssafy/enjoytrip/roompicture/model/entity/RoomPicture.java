package com.ssafy.enjoytrip.roompicture.model.entity;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Alias("roompicture")
public class RoomPicture {

    private Long id;
    private Long roomId;
    private String picture;
    private LocalDateTime createdat;
    private LocalDateTime updatedat;

    public static RoomPicture of(String picture, Long roomId){
        return RoomPicture.builder()
            .roomId(roomId)
            .picture(picture)
            .build();
    }
}
