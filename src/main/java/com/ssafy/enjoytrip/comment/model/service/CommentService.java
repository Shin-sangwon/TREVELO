package com.ssafy.enjoytrip.comment.model.service;

import com.ssafy.enjoytrip.comment.model.dto.CommentDto;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {

    void write(CommentDto commentDto) throws SQLException;

    void delete(long commentId) throws SQLException;

    List<CommentDto> getlist(long boardId) throws SQLException;

    void update(CommentDto commentDto) throws SQLException;
}
