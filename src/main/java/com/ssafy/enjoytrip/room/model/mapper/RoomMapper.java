package com.ssafy.enjoytrip.room.model.mapper;

import com.ssafy.enjoytrip.room.model.dto.request.RoomCreateRequestDto;
import com.ssafy.enjoytrip.room.model.dto.request.RoomUpdateRequestDto;
import com.ssafy.enjoytrip.room.model.dto.response.RoomListResponseDto;
import com.ssafy.enjoytrip.room.model.entity.Room;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomMapper {

    List<Room> findAll();

    Optional<Room> findById(Long id);

    Long save(RoomCreateRequestDto roomCreateRequestDto);

    void update(RoomUpdateRequestDto roomUpdateRequestDto);

    void delete(Long id);

    List<RoomListResponseDto> findAllWithPicture();
}
