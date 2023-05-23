package com.ssafy.enjoytrip.member.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

    @Getter
    @AllArgsConstructor
    public enum Role {
        ADMIN(9), SELLER(4), MEMBER(1);

        private final Integer authLevel;
    }
