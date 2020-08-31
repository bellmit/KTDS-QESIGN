package com.ktds.esign.client.example.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class VideoReq {

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VideoDto {
        private String videoUrl;
        private Integer videoPlaySec;
    }

}
