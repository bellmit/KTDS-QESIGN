package com.ktds.esign.client.example.payload;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ExUserNotiRes {

    @Data
    public static class ExUserDto {
        private String userId;
        private String empNo;
        private String empName;
        private String email;
        private String phone;
        private String deptId;
        private String signFilePath;
        private String signFileName;
        private ExCompanyDto company;
        private final List<ExUserNotiDto> userNotis = new ArrayList<>();
    }

    @Data
    public static class ExUserNotiDto {
        private String notiType;
        private String notiDirectType;
    }

    @Data
    public static class ExCompanyDto {
        private String cmpnId;
        private String cmpnName;
        private String diplayOrder;
    }

}
