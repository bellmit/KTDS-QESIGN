package com.ktds.esign.client.example.payload;

import lombok.Data;

import java.time.LocalDateTime;

public class ListRes {

    @Data
    public static class ExUserPledgeDto {
        private Long id;
        private String userPledgeStatus;
        private String pledgeType;
        private String pledgeName;
        // 서약 요청일
        private LocalDateTime startDt;
        // 서약 마감일
        private LocalDateTime endDt;
        // 요청부서
        private String reqDept;
        // 요청자
        private String reqUser;
    }
}
