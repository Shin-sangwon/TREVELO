package com.ssafy.enjoytrip.comment.model.mapper;

import com.ssafy.enjoytrip.comment.model.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CommentMapper {

    void write(CommentDto commentDto) throws SQLException;

    void delete(long commentId) throws SQLException;

    List<CommentDto> getlist(long boardId) throws SQLException;

    void update(CommentDto commentDto) throws SQLException;
}
