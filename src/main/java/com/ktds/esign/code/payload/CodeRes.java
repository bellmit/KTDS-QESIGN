package com.ktds.esign.code.payload;

import lombok.Data;

public class CodeRes {

    @Data
    public static class CommonCodeDto {
        private String groupCode;
        private String groupDesc;
        private String code;
        private String codeDesc;
        private String displayOrder;
    }

}
