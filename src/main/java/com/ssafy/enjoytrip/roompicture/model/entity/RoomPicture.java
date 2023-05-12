package com.ssafy.enjoytrip.roompicture.model.entity;

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
public class RoomPicture {

    private Long id;
    private Long roomId;
    private String picture;
    private LocalDateTime createdat;
    private LocalDateTime updatedat;
}
