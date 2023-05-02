package com.ssafy.enjoytrip.board.model.dto;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

public class BoardDto {
    long boardId;
    long MemberId;
    String title;
    String content;
    long hit;
    String boardTag;
    LocalDateTime createdat;
    LocalDateTime updatedat;

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public long getMemberId() {
        return MemberId;
    }

    public void setMemberId(long memberId) {
        MemberId = memberId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getHit() {
        return hit;
    }

    public void setHit(long hit) {
        this.hit = hit;
    }

    public String getBoardTag() {
        return boardTag;
    }

    public void setBoardTag(String boardTag) {
        this.boardTag = boardTag;
    }

    public LocalDateTime getCreatedat() {
        return createdat;
    }

    public void setCreatedat(LocalDateTime createdat) {
        this.createdat = createdat;
    }

    public LocalDateTime getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(LocalDateTime updatedat) {
        this.updatedat = updatedat;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "boardId=" + boardId +
                ", MemberId=" + MemberId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", hit=" + hit +
                ", boardTag='" + boardTag + '\'' +
                ", createdat=" + createdat +
                ", updatedat=" + updatedat +
                '}';
    }
}
