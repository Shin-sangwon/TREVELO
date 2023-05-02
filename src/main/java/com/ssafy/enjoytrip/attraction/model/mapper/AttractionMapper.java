package com.ssafy.enjoytrip.attraction.model.mapper;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionSearchDto;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttractionMapper {

    List<AttractionDto> searchBySidoGugunContent(AttractionSearchDto attractionSearchDto);

    List<AttractionDto> searchBySidoGugun(AttractionSearchDto attractionSearchDto);

    List<AttractionDto> searchBySidoContent(AttractionSearchDto attractionSearchDto);

    List<AttractionDto> searchBySido(AttractionSearchDto attractionSearchDto);

    List<AttractionDto> searchByContent(AttractionSearchDto attractionSearchDto);

    List<AttractionDto> searchByNot(AttractionSearchDto attractionSearchDto);

}
