package com.ssafy.enjoytrip.attraction.model.mapper;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionFavoriteDto;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionSearchDto;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionDto;
import com.ssafy.enjoytrip.member.model.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface AttractionMapper {

    List<AttractionDto> searchBySidoGugunContent(AttractionSearchDto attractionSearchDto);

    List<AttractionDto> searchBySidoGugun(AttractionSearchDto attractionSearchDto);

    List<AttractionDto> searchBySidoContent(AttractionSearchDto attractionSearchDto);

    List<AttractionDto> searchBySido(AttractionSearchDto attractionSearchDto);

    List<AttractionDto> searchByContent(AttractionSearchDto attractionSearchDto);

    List<AttractionDto> searchByNot(AttractionSearchDto attractionSearchDto);

    List<AttractionDto> favoriteList(long memberId);

    void favoriteRegister (AttractionFavoriteDto attractionFavoriteDto);

    int findDuplication(AttractionFavoriteDto attractionFavoriteDto);

    AttractionDto favoriteView(long contentId);

    void favoriteDelete(AttractionFavoriteDto attractionFavoriteDto);

}
