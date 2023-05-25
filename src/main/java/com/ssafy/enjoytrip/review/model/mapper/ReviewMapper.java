package com.ssafy.enjoytrip.review.model.mapper;

import com.ssafy.enjoytrip.review.model.entity.Review;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {

    List<Review> findAllByRoomId(Long roomId);
}
