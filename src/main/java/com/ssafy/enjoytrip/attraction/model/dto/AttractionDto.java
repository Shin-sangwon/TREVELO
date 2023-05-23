package com.ssafy.enjoytrip.attraction.model.dto;

public class AttractionDto {
    long contentId;
    String title;
    String addr;
    long sidoCode;
    long gugunCode;
    double latitude;
    double longitude;
    long contentType;

    String first_image;

    public String getFirst_image() {
        return first_image;
    }

    public void setFirst_image(String first_image) {
        this.first_image = first_image;
    }

    public long getContentType() {
        return contentType;
    }

    public void setContentType(long contentType) {
        this.contentType = contentType;
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
