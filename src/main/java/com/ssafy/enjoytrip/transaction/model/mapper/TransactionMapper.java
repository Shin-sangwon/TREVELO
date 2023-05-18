package com.ssafy.enjoytrip.transaction.model.mapper;

import com.ssafy.enjoytrip.transaction.model.dto.response.TransactionResponseDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionMapper {

    List<TransactionResponseDto> findAllByMemberId(Long memberId);
}
