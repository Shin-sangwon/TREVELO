package com.ssafy.enjoytrip.roompicture.model.service;

import com.ssafy.enjoytrip.roompicture.model.mapper.RoomPictureMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomPictureServiceImpl implements RoomPictureService{

    private final RoomPictureMapper roomPictureMapper;
}
