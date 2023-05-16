package com.ssafy.enjoytrip.room.model.dto.response;

import com.ssafy.enjoytrip.room.model.entity.Room;
import com.ssafy.enjoytrip.roompicture.model.entity.RoomPicture;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class RoomResponseDto {

    private Long id;
    private Long ownerId;
    private String roomName;
    private String address;
    private String introduce;
    private Long pricePerNight;
    private LocalDateTime createdat;
    private LocalDateTime updatedat;
    // TODO 코드 어떻게 매핑??
    private int sidoCode;
    private int gugunCode;

    private List<String> roomPictures;

    public static RoomResponseDto from(Room entity) {
        return RoomResponseDto.builder()
                              .id(entity.getId())
                              .ownerId(entity.getOwnerId())
                              .roomName(entity.getRoomName())
                              .address(entity.getAddress())
                              .introduce(entity.getIntroduce())
                              .pricePerNight(entity.getPricePerNight())
                              .createdat(entity.getCreatedat())
                              .updatedat(entity.getUpdatedat())
                              .build();
    }

    public void mapPictureToRoom(List<RoomPicture> pictureList) {

        roomPictures = pictureList.stream()
                                  .map(RoomPicture::getPicture)
                                  .collect(Collectors.toList());
    }
}
