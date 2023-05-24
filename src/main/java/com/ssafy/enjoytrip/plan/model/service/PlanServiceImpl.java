package com.ssafy.enjoytrip.plan.model.service;

import com.ssafy.enjoytrip.plan.model.dto.PlanDto;
import com.ssafy.enjoytrip.plan.model.mapper.PlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService{

    @Autowired
    private final PlanMapper planMapper;

    @Autowired
    public PlanServiceImpl(PlanMapper planMapper) {
        this.planMapper = planMapper;
    }

    @Override
    public List<PlanDto> getList(long memberId) throws SQLException {
        return planMapper.getList(memberId);
    }

    @Override
    public PlanDto view(long planId) throws SQLException {
        return planMapper.view(planId);
    }

    @Override
    public void update(PlanDto planDto) throws SQLException {
        planMapper.update(planDto);
    }

    @Override
    public void delete(long planId) throws SQLException {
        planMapper.delete(planId);
    }

    @Override
    public void write(PlanDto planDto) throws SQLException {
        planMapper.write(planDto);
    }
}
