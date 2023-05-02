package com.ssafy.enjoytrip.attraction.model.service;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionSearchDto;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionDto;

import java.util.List;

public interface AttractionService {

    List<AttractionDto> searchBySidoGugunContent(AttractionSearchDto attractionSearchDto);

    List<AttractionDto> searchBySidoGugun(AttractionSearchDto attractionSearchDto);

    List<AttractionDto> searchBySidoContent(AttractionSearchDto attractionSearchDto);

    List<AttractionDto> searchBySido(AttractionSearchDto attractionSearchDto);

    List<AttractionDto> searchByContent(AttractionSearchDto attractionSearchDto);

    List<AttractionDto> searchByNot(AttractionSearchDto attractionSearchDto);
}
