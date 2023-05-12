package com.ssafy.enjoytrip.room.model.service;

import com.ssafy.enjoytrip.room.model.mapper.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService{

    private final RoomMapper roomMapper;
}
