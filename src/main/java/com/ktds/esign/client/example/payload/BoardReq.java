package com.ktds.esign.client.example.payload;


import com.ktds.esign.client.example.domain.ExBoard;
import lombok.Data;

import java.util.List;

public class BoardReq {

    @Data
    public static class BoardDto {

        private String title;

        private String content;

        private Integer count;

        private List<String> images;

        public ExBoard toEntity() {
            return ExBoard.builder().title(title).content(content).build();
        }

    }

}
