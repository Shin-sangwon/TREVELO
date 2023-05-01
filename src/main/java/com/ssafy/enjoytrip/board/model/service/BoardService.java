package com.ssafy.enjoytrip.board.model.service;


import com.ssafy.enjoytrip.board.model.dto.BoardDto;

import java.sql.SQLException;
import java.util.List;

public interface BoardService {

    void write(BoardDto boardDto) throws SQLException;

    List<BoardDto> getlist() throws SQLException;

    BoardDto view(long boardId) throws SQLException;

    void delete(long boardId) throws SQLException;

    void update(BoardDto boardDto) throws SQLException;
}
