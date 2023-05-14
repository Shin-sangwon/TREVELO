package com.ssafy.enjoytrip.roompicture.model.mapper;

import com.ssafy.enjoytrip.roompicture.model.entity.RoomPicture;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomPictureMapper {

    List<RoomPicture> findAllByRoomId(Long id);

    void save(RoomPicture roomPicture);
}
