package com.ktds.esign.client.example.payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Data
public class UploadModel {
    private String extraField;
    private MultipartFile[] files;
}
