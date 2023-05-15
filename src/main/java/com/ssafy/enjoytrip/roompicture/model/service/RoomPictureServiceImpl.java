package com.ssafy.enjoytrip.roompicture.model.service;

import com.ssafy.enjoytrip.roompicture.model.entity.RoomPicture;
import com.ssafy.enjoytrip.roompicture.model.mapper.RoomPictureMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class RoomPictureServiceImpl implements RoomPictureService {

    private final RoomPictureMapper roomPictureMapper;

    @Transactional(readOnly = true)
    @Override
    public List<RoomPicture> getRoomPicture(Long id) {

        return roomPictureMapper.findAllByRoomId(id);
    }

    @Transactional
    @Override
    public void saveAll(List<String> imageUrl, Long roomId) {

        imageUrl.stream()
                .map(url -> RoomPicture.of(url, roomId))
                .forEach(roomPictureMapper::save);
    }

    @Transactional
    @Override
    public void update(Long id, List<MultipartFile> imageList) {


    }


    @Transactional
    @Override
    public void deleteAll(Long id) {

        roomPictureMapper.deleteAll(id);

    }




}
