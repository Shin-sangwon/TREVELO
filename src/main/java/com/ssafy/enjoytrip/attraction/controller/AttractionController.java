package com.ssafy.enjoytrip.attraction.controller;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionSearchDto;
import com.ssafy.enjoytrip.attraction.model.service.AttractionService;
import com.ssafy.enjoytrip.member.model.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping("/api/v1/attraction")
@RestController
public class AttractionController {

    private final AttractionService attractionService;

    @Autowired
    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }


    @PostMapping("/search")
    public ResponseEntity<?> search(@AuthenticationPrincipal Member member, @RequestParam(value = "title") String title, @RequestParam(value = "sidoCode", defaultValue = "-1") long sidoCode, @RequestParam(value = "gugunCode", defaultValue = "-1") long gugunCode, @RequestParam(value = "contentType", defaultValue = "-1") long contentType){
        AttractionSearchDto attractionSearchDto = new AttractionSearchDto();

        List<AttractionDto> list = new ArrayList<>();

        attractionSearchDto.setTitle(title);
        attractionSearchDto.setGugunCode(gugunCode);
        attractionSearchDto.setSidoCode(sidoCode);
        attractionSearchDto.setContentType(contentType);

        if(sidoCode != -1 && gugunCode != -1 && contentType != -1){
            list = attractionService.searchBySidoGugunContent(attractionSearchDto);
        }else if(sidoCode != -1 && gugunCode != -1 && contentType == -1){
            list = attractionService.searchBySidoGugun(attractionSearchDto);
        }else if(sidoCode != -1 && gugunCode == -1 && contentType != -1){
            list = attractionService.searchBySidoContent(attractionSearchDto);
        }else if(sidoCode != -1 && gugunCode == -1 && contentType == -1){
            list = attractionService.searchBySido(attractionSearchDto);
        }else if(sidoCode == -1 && gugunCode == -1 && contentType != -1){
            list = attractionService.searchByContent(attractionSearchDto);
        }else if(sidoCode == -1 && gugunCode == -1 && contentType == -1){
            list = attractionService.searchByNot(attractionSearchDto);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
