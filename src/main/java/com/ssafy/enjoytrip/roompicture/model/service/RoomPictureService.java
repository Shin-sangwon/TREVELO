package com.ssafy.enjoytrip.roompicture.model.service;

import com.ssafy.enjoytrip.roompicture.model.entity.RoomPicture;
import java.util.List;

public interface RoomPictureService{

    List<RoomPicture> getRoomPicture(Long id);

    void save(List<String> imageUrl, Long roomId);
}
