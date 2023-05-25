package com.ssafy.enjoytrip.review.model.service;

import com.ssafy.enjoytrip.review.model.dto.response.ReviewResponseDto;
import com.ssafy.enjoytrip.review.model.mapper.ReviewMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;

    @Transactional(readOnly = true)
    @Override
    public List<ReviewResponseDto> findAllByRoomId(Long roomId) {

        return reviewMapper.findAllByRoomId(roomId)
                           .stream()
                           .map(ReviewResponseDto::from)
                           .collect(
                               Collectors.toList());
    }
}
