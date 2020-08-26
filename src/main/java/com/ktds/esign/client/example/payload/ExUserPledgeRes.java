package com.ktds.esign.client.example.payload;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ExUserPledgeRes {

    //@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @AllArgsConstructor(staticName = "of")
    @Data
    public static class ResultCountDto implements Serializable {
        private static final long serialVersionUID = -2563365853870085478L;
        private Long standbyCount;
        private Long proceedingCount;
        private Long completeCount;
    }

    @Data
    public static class FindDto {
        private Long id;
        private String pledgeAcceptType;
        private String pledgeAcceptDesc;
        private String pledgeType;
        private String pledgeDesc;
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
