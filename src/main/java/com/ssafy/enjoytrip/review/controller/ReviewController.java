package com.ssafy.enjoytrip.review.controller;

import com.ssafy.enjoytrip.review.model.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
@RestController
public class ReviewController {

    private final ReviewService reviewService;

}
