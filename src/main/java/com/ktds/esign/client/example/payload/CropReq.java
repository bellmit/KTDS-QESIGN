package com.ktds.esign.client.example.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class CropReq {

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CropFileDto {
        private MultipartFile file;
        private String userId;
        private String filename;
    }


}
