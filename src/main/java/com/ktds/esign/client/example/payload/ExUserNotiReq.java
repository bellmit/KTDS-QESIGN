package com.ktds.esign.client.example.payload;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ExUserNotiReq {

    @Data
    public static class CreateDto {
        private String signFilePath;
        private String signFileName;
        private List<ExUserNotiDto> userNotis = new ArrayList<>();
    }

    @Data
    public static class ExUserNotiDto {
        private String notiType;
        private String directionType;
    }

}
