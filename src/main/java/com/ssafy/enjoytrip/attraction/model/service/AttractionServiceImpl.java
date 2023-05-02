package com.ssafy.enjoytrip.attraction.model.service;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionSearchDto;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.mapper.AttractionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttractionServiceImpl implements AttractionService{

    private final AttractionMapper attractionMapper;

    @Autowired
    public AttractionServiceImpl(AttractionMapper attractionMapper) {
        this.attractionMapper = attractionMapper;
    }


    @Override
    public List<AttractionDto> searchBySidoGugunContent(AttractionSearchDto attractionSearchDto) {
        return attractionMapper.searchBySidoGugunContent(attractionSearchDto);
    }

    @Override
    public List<AttractionDto> searchBySidoGugun(AttractionSearchDto attractionSearchDto) {
        return attractionMapper.searchBySidoGugun(attractionSearchDto);
    }

    @Override
    public List<AttractionDto> searchBySidoContent(AttractionSearchDto attractionSearchDto) {
        return attractionMapper.searchBySidoContent(attractionSearchDto);
    }

    @Override
    public List<AttractionDto> searchBySido(AttractionSearchDto attractionSearchDto) {
        return attractionMapper.searchBySido(attractionSearchDto);
    }

    @Override
    public List<AttractionDto> searchByContent(AttractionSearchDto attractionSearchDto) {
        return attractionMapper.searchByContent(attractionSearchDto);
    }

    @Override
    public List<AttractionDto> searchByNot(AttractionSearchDto attractionSearchDto) {
        return attractionMapper.searchByNot(attractionSearchDto);
    }
}
