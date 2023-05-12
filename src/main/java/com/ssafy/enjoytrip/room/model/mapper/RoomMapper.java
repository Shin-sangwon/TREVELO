package com.ssafy.enjoytrip.room.model.mapper;

import com.ssafy.enjoytrip.room.model.entity.Room;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomMapper {

    List<Room> findAll();
}
