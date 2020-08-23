package com.ktds.esign.common.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {

    private boolean success;

    private String code;

    private String message;

    private String pagination;

}
