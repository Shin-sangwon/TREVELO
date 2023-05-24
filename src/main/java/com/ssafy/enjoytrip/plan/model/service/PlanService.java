package com.ssafy.enjoytrip.plan.model.service;

import com.ssafy.enjoytrip.plan.model.dto.PlanDto;

import java.sql.SQLException;
import java.util.List;

public interface PlanService {
    List<PlanDto> getList(long memberId) throws SQLException;

    PlanDto view(long planId) throws SQLException;

    void update(PlanDto planDto) throws SQLException;

    void delete(long planId) throws SQLException;

    void write(PlanDto planDto) throws SQLException;
}
