package com.ssafy.enjoytrip.attraction.model.dto;

public class AttractionFavoriteDto {
    long contentId;
    long memberId;

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }
}
