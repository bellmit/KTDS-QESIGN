package com.ktds.esign.client.example.payload;

import com.ktds.esign.common.enums.PledgeType;
import com.ktds.esign.common.enums.UserPledgeStatusType;
import lombok.Data;

import java.time.LocalDateTime;

public class ListRes {

    @Data
    public static class ExUserPledgeDto {
        private Long id;
        private UserPledgeStatusType userPledgeStatus;
        private PledgeType pledgeType;
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
