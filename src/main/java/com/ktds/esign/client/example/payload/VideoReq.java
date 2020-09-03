package com.ktds.esign.client.example.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

public class VideoReq {

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VideoDto {
        private String videoUrl;
        private Integer videoPlaySec;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VideoUploadDto {
        private MultipartFile file;
        private String formName;
        private String formDesc;
        private String formType;
    }

}
