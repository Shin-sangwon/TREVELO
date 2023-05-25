package com.ssafy.enjoytrip.room.model.service;

import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.room.model.dto.request.RoomCreateRequestDto;
import com.ssafy.enjoytrip.room.model.dto.request.RoomUpdateRequestDto;
import com.ssafy.enjoytrip.room.model.dto.response.RoomListResponseDto;
import com.ssafy.enjoytrip.room.model.dto.response.RoomResponseDto;
import java.util.List;

public interface RoomService {

    List<RoomListResponseDto> findAll();

    List<RoomListResponseDto> findAllWithPicture();
    RoomResponseDto findById(Long id);

    Long save(RoomCreateRequestDto roomCreateRequestDto, Member member);

    void update(RoomUpdateRequestDto roomUpdateRequestDto);

    void delete(Long id);

    void deleteWithPicture(Long id);

    List<RoomListResponseDto> findAllByKeywordWithPicture(String keyword);
}
