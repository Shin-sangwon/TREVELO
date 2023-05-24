package com.ssafy.enjoytrip.board.model.service;

import com.ssafy.enjoytrip.board.model.dto.BoardDto;
import com.ssafy.enjoytrip.board.model.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private final BoardMapper boardMapper;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public void write(BoardDto boardDto) throws SQLException {
        boardMapper.write(boardDto);
    }

    @Override
    public List<BoardDto> getlist() throws SQLException {
        return boardMapper.getlist();
    }

    @Override
    public BoardDto view(long boardId) throws SQLException {
        return boardMapper.view(boardId);
    }

    @Override
    public void delete(long boardId) throws SQLException {
        boardMapper.delete(boardId);
    }

    @Override
    public void update(BoardDto boardDto) throws SQLException {
        boardMapper.update(boardDto);
    }

    @Override
    public void uphit(long boardId) throws SQLException {
        boardMapper.uphit(boardId);
    }
}
