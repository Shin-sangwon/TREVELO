package com.ssafy.enjoytrip.roompicture.model.service;

import com.ssafy.enjoytrip.roompicture.model.entity.RoomPicture;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface RoomPictureService{

    List<RoomPicture> getRoomPicture(Long id);

    void saveAll(List<String> imageUrl, Long roomId);

    void update(Long id, List<MultipartFile> imageList);
}
