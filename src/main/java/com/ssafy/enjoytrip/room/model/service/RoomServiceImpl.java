package com.ssafy.enjoytrip.room.model.service;

import com.ssafy.enjoytrip.global.exception.ErrorCode;
import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.room.exception.RoomException;
import com.ssafy.enjoytrip.room.model.dto.request.RoomCreateRequestDto;
import com.ssafy.enjoytrip.room.model.dto.request.RoomUpdateRequestDto;
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
    public List<RoomListResponseDto> findAll() {

        return roomMapper.findAll()
                         .stream()
                         .map(RoomListResponseDto::from)
                         .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoomListResponseDto> findAllWithPicture() {

        List<RoomListResponseDto> roomList = roomMapper.findAllWithPicture();

        roomList.forEach(RoomListResponseDto::mapPictureToRoom);

        return roomList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoomListResponseDto> findAllByKeywordWithPicture(String keyword) {

        List<RoomListResponseDto> searchRoomList = roomMapper.findAllByKeywordWithPicture(keyword);

        searchRoomList.forEach(RoomListResponseDto::mapPictureToRoom);

        return searchRoomList;
    }

    @Transactional(readOnly = true)
    @Override
    public RoomResponseDto findById(Long id) {

        Optional<Room> room = roomMapper.findById(id);

        if (!room.isPresent()) {
            throw new RoomException(ErrorCode.ROOM_NOT_FOUND,
                ErrorCode.ROOM_NOT_FOUND.getMessage());
        }

        RoomResponseDto roomResponseDto = RoomResponseDto.from(room.get());
        List<RoomPicture> pictureList = roomPictureService.getRoomPicture(id);

        roomResponseDto.mapPictureToRoom(pictureList);
        return roomResponseDto;
    }

    @Transactional
    @Override
    public Long save(RoomCreateRequestDto roomCreateRequestDto, Member member) {

        roomCreateRequestDto.mapOwnerId(member.getId());

        return roomMapper.save(roomCreateRequestDto);
    }

    @Transactional
    @Override
    public void update(RoomUpdateRequestDto roomUpdateRequestDto) {

        roomMapper.update(roomUpdateRequestDto);
    }

    @Transactional
    @Override
    public void delete(Long id) {

        roomMapper.delete(id);
    }

    @Override
    @Transactional
    public void deleteWithPicture(Long id) {

        roomPictureService.deleteAll(id);
        delete(id);

    }




}
