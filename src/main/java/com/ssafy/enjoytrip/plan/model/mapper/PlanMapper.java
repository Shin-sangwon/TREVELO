package com.ssafy.enjoytrip.plan.model.mapper;

import com.ssafy.enjoytrip.plan.model.dto.PlanDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface PlanMapper {

    List<PlanDto> getList(long memberId) throws SQLException;

    PlanDto view(long planId) throws SQLException;

    void update(PlanDto planDto) throws SQLException;

    void delete(long planId) throws SQLException;

    void write(PlanDto planDto) throws SQLException;
}
