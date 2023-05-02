package com.ssafy.enjoytrip.attraction.controller;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionFavoriteDto;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionSearchDto;
import com.ssafy.enjoytrip.attraction.model.service.AttractionService;
import com.ssafy.enjoytrip.member.model.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<AttractionDto>> search(@AuthenticationPrincipal Member member, @RequestParam(value = "title") String title, @RequestParam(value = "sidoCode", defaultValue = "-1") long sidoCode, @RequestParam(value = "gugunCode", defaultValue = "-1") long gugunCode, @RequestParam(value = "contentType", defaultValue = "-1") long contentType){
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

    @GetMapping("/favorite")
    public ResponseEntity<List<AttractionDto>> favorite(@AuthenticationPrincipal Member member){
        List<AttractionDto> list = attractionService.favoriteList(member.getId());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/favorite/regist/{contentId}")
    public ResponseEntity<List<AttractionDto>> regist(@AuthenticationPrincipal Member member, @PathVariable("contentId") long contentId){

        AttractionFavoriteDto attractionFavoriteDto = new AttractionFavoriteDto();
        attractionFavoriteDto.setMemberId(member.getId());
        attractionFavoriteDto.setContentId(contentId);

        // 해당 member가 ContentId를 이미 가지고 있는지 확인하기 위함
        int N = attractionService.findDuplication(attractionFavoriteDto);

        // 0이면 결과값이 없으므로 등록 가능
        if(N == 0){
            attractionService.favoriteRegister(attractionFavoriteDto);
            // 1이면 이미 해당 사용자는 해당 contentid를 저장한 상태
        }else {
            log.info("이미 저장한 관광지입니다.");
        }

        List<AttractionDto> list = attractionService.favoriteList(member.getId());

        return new ResponseEntity<List<AttractionDto>>(list, HttpStatus.OK);
    }

    @PostMapping("/favorite/view/{contentId}")
    public ResponseEntity<AttractionDto> view(@AuthenticationPrincipal Member member, @PathVariable("contentId") long contentId){


        AttractionDto attractionDto = attractionService.favoriteView(contentId);

        return new ResponseEntity<>(attractionDto, HttpStatus.OK);
    }

    @PostMapping("/favorite/delete/{contentId}")
    public ResponseEntity<List<AttractionDto>> delete(@AuthenticationPrincipal Member member, @PathVariable("contentId") long contentId){
        AttractionFavoriteDto attractionFavoriteDto = new AttractionFavoriteDto();

        attractionFavoriteDto.setMemberId(member.getId());
        attractionFavoriteDto.setContentId(contentId);

        attractionService.favoriteDelete(attractionFavoriteDto);

        List<AttractionDto> list = attractionService.favoriteList(member.getId());

        return new ResponseEntity<List<AttractionDto>>(list, HttpStatus.OK);
    }

}
