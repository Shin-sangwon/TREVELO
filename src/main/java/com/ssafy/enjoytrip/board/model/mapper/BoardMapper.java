package com.ssafy.enjoytrip.board.model.mapper;

import com.ssafy.enjoytrip.board.model.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface BoardMapper {
    void write(BoardDto boardDto) throws SQLException;

    List<BoardDto> getlist() throws SQLException;

    BoardDto view(long boardId) throws SQLException;

    void delete(long boardId) throws SQLException;

    void update(BoardDto boardDto) throws SQLException;

    void uphit(long boardId) throws SQLException;
}
