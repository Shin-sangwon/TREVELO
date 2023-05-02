package com.ssafy.enjoytrip.attraction.model.dto;

public class AttractionSearchDto {
    String title;
    long sidoCode;
    long gugunCode;
    long contentType;

    public long getContentType() {
        return contentType;
    }

    public void setContentType(long contentType) {
        this.contentType = contentType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getSidoCode() {
        return sidoCode;
    }

    public void setSidoCode(long sidoCode) {
        this.sidoCode = sidoCode;
    }

    public long getGugunCode() {
        return gugunCode;
    }

    public void setGugunCode(long gugunCode) {
        this.gugunCode = gugunCode;
    }

}
