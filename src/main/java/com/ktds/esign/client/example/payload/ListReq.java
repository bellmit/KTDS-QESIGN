package com.ktds.esign.client.example.payload;

import com.ktds.esign.common.enums.PledgeTypeCode;
import com.ktds.esign.common.enums.PledgeUserStatusCode;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

public class ListReq {

    @Data
    public static class ExUserPledgeDto {
        private Long id;
        private PledgeUserStatusCode pledgeUserStatus;
        private PledgeTypeCode pledgeType;
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
