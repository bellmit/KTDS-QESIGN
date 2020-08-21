package com.ktds.esign.client.example.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class FileUploadReq {

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FileUploadDto {
        private Integer uploaded;
        private String filename;
        private String url;
    }

}
