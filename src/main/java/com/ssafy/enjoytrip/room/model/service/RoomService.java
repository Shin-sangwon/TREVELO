package com.ssafy.enjoytrip.room.model.service;

import com.ssafy.enjoytrip.room.model.dto.response.RoomListResponseDto;
import com.ssafy.enjoytrip.room.model.dto.response.RoomResponseDto;
import java.util.List;

public interface RoomService {

    List<RoomListResponseDto> findAll() throws Exception;

    RoomResponseDto findById(Long id);
}
