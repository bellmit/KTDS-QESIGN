package com.ktds.esign.client.example.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileUploadDto {
    private Integer uploaded;
    private String filename;
    private String url;


}
