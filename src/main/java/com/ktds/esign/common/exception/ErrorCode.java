package com.ktds.esign.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	USER_NOT_FOUND("APP_ERROR_001", "해당 사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND.value()),
	USER_EXISTS("APP_ERROR_002", "이미 등록된 사용자입니다.", HttpStatus.BAD_REQUEST.value()),
	EMAIL_DUPLICATION("APP_ERROR_003", "사용 중인 이메일입니다.", HttpStatus.BAD_REQUEST.value()),
	STORAGE_FAILED("APP_ERROR_004", "파일 처리 시 문제가 발생했습니다.", HttpStatus.BAD_REQUEST.value()),
	AUTHENTICATION_FAILED("APP_ERROR_005", "이메일과 비밀번호가 정확하지 않습니다.", HttpStatus.UNAUTHORIZED.value()),
	JWT_TOKEN_INVALID("APP_ERROR_006", "토큰이 유효하지 않습니다.", HttpStatus.UNAUTHORIZED.value()),
	INPUT_VALUE_INVALID("APP_ERROR_007", "입력 값이 올바르지 않습니다.", HttpStatus.BAD_REQUEST.value()),
	METHOD_NOT_ALLOWED("APP_ERROR_008","지원되지 않는 메서드 요청입니다.", HttpStatus.METHOD_NOT_ALLOWED.value()),
	RESOURCE_NOT_FOUND("APP_ERROR_009","해당 자원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND.value()),
	CODE_NOT_FOUND("APP_ERROR_010","해당 코드를 찾을 수 없습니다.", HttpStatus.NOT_FOUND.value()),
	FILE_PROCESS_FAILED("APP_ERROR_011","파일 요청 처리 중 오류가 발생했습니다.", HttpStatus.BAD_REQUEST.value()),
	UNKNOWN_EXCEPTION("SYSTEM_UNKNOWN_ERROR","알 수 없는 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR.value());
	
	private final String code;
	private final String message;
	private final int status;

	ErrorCode(String code, String message, int status) {
		this.code = code;
		this.message = message;
		this.status = status;
	}

}
