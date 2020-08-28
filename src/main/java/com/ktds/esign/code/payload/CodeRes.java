package com.ktds.esign.code.payload;

import lombok.Data;

public class CodeRes {

    @Data
    public static class CodeDto {
        private String codeId;
        private String groupCodeId;
        private String codeDesc;
        private String displayOrder;
    }

    @Data
    public static class GroupCodeDto {
        private String groupCodeId;
        private String groupCodeDesc;
    }

}
