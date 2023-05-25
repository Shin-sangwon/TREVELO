package com.ssafy.enjoytrip.review.controller;

import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.review.model.dto.response.ReviewResponseDto;
import com.ssafy.enjoytrip.review.model.service.ReviewService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{roomId}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByRoomId(@PathVariable("roomId") Long roomId, @AuthenticationPrincipal Member member) {


        return ResponseEntity.ok().body(reviewService.findAllByRoomId(roomId));
    }

}
