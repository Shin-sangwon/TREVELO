package com.ssafy.enjoytrip.room.model.mapper;

import com.ssafy.enjoytrip.room.model.entity.Room;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomMapper {

    List<Room> findAll();

    Optional<Room> findById(Long id);
}
