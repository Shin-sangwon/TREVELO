package com.ssafy.enjoytrip.comment.model.service;

import com.ssafy.enjoytrip.comment.model.dto.CommentDto;
import com.ssafy.enjoytrip.comment.model.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public void write(CommentDto commentDto) throws SQLException {
        commentMapper.write(commentDto);
    }

    @Override
    public void delete(long commentId) throws SQLException {
        commentMapper.delete(commentId);
    }

    @Override
    public List<CommentDto> getlist(long boardId) throws SQLException {
        return commentMapper.getlist(boardId);
    }

    @Override
    public void update(CommentDto commentDto) throws SQLException {
        commentMapper.update(commentDto);
    }
}
