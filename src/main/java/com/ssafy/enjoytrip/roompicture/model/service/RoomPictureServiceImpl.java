package com.ssafy.enjoytrip.roompicture.model.service;

import com.ssafy.enjoytrip.global.service.AmazonS3Service;
import com.ssafy.enjoytrip.roompicture.model.entity.RoomPicture;
import com.ssafy.enjoytrip.roompicture.model.mapper.RoomPictureMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RoomPictureServiceImpl implements RoomPictureService {

    private final RoomPictureMapper roomPictureMapper;
    private final AmazonS3Service amazonS3Service;

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
    public void deleteAll(Long id) {

        roomPictureMapper.deleteAll(id);

    }

    @Transactional
    @Override
    public void deleteAllWithS3(Long id) {
        List<RoomPicture> deletedPictureList = getRoomPicture(id);
        // 파일 버킷에서 지우기
        deletedPictureList.stream()
                          .map(RoomPicture::getPicture)
                          .forEach(amazonS3Service::deleteFile);
        // db에서 지우기
        deleteAll(id);
    }


}
