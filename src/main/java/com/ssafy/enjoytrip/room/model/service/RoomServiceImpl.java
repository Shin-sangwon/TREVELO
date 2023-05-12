package com.ssafy.enjoytrip.room.model.service;

import com.ssafy.enjoytrip.room.model.dto.response.RoomListResponseDto;
import com.ssafy.enjoytrip.room.model.mapper.RoomMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {

    private final RoomMapper roomMapper;

    @Transactional(readOnly = true)
    @Override
    public List<RoomListResponseDto> findAll() throws Exception {

        return roomMapper.findAll()
                         .stream()
                         .map(RoomListResponseDto::new)
                         .collect(Collectors.toList());
    }
}
