package com.ssafy.enjoytrip.review.model.service;

import com.ssafy.enjoytrip.review.model.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewMapper reviewMapper;
}
