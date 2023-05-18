package com.ssafy.enjoytrip.transaction.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionType {

    MILEAGE_DEPOSIT("마일리지 충전"),
    MILEAGE_WITHDRAW("마일리지 출금"),
    ROOM_RESERVATION("예약금 결제"),
    RESERVATION_CANCEL("예약금 환불");

    private final String description;

}
