package com.ssafy.enjoytrip.room.model.service;

import com.ssafy.enjoytrip.global.ErrorCode;
import com.ssafy.enjoytrip.room.exception.RoomException;
import com.ssafy.enjoytrip.room.model.dto.response.RoomListResponseDto;
import com.ssafy.enjoytrip.room.model.dto.response.RoomResponseDto;
import com.ssafy.enjoytrip.room.model.entity.Room;
import com.ssafy.enjoytrip.room.model.mapper.RoomMapper;
import com.ssafy.enjoytrip.roompicture.model.entity.RoomPicture;
import com.ssafy.enjoytrip.roompicture.model.service.RoomPictureService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {

    private final RoomMapper roomMapper;
    private final RoomPictureService roomPictureService;
    @Transactional(readOnly = true)
    @Override
    public List<RoomListResponseDto> findAll() throws Exception {

        return roomMapper.findAll()
                         .stream()
                         .map(RoomListResponseDto::from)
                         .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public RoomResponseDto findById(Long id) {

        Optional<Room> room = roomMapper.findById(id);

        if(!room.isPresent()) {
            throw new RoomException(ErrorCode.ROOM_NOT_FOUND, ErrorCode.ROOM_NOT_FOUND.getMessage());
        }

        RoomResponseDto roomResponseDto = RoomResponseDto.from(room.get());
        List<RoomPicture> pictureList = roomPictureService.getRoomPicture(id);

        roomResponseDto.mapPictureToRoom(pictureList);
        return roomResponseDto;
    }


}
