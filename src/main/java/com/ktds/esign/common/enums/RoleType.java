package com.ktds.esign.common.enums;

import com.ktds.esign.common.enums.converter.EnumConverter;
import com.ktds.esign.common.enums.converter.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum RoleType implements IEnum<String> {
    ROLE_USER("ROLE_USER", "일반 사용자"),
    ROLE_FORM("ROLE_FORM", "양식 담당자"),
    ROLE_PLEDGE("ROLE_PLEDGE", "서약 업무 담당자"),
    ROLE_SYSTEM("ROLE_SYSTEM", "시스템 관리자");

    private String code; // db_code
    private String desc; // db_description

    // code -> ENUM TYPE
    public static RoleType getTypeFromCode(String val) {
        return Arrays.stream(values()).filter(e -> e.getCode().equals(val)).findAny().orElse(null);
    }

    // desc -> ENUM TYPE
    public static RoleType getTypeFromDesc(String val) {
        return Arrays.stream(values()).filter(e -> e.getDesc().equals(val)).findAny().orElse(null);
    }

    // ENUM TYPE -> code
    public static String getCodeFromType(RoleType type) {
        return Optional.ofNullable(type).isPresent() ? type.getCode() : null;
    }

    // ENUM TYPE -> desc
    public static String getDescFromType(RoleType type) {
        return Optional.ofNullable(type).isPresent() ? type.getDesc() : null;
    }

    public static class Converter extends EnumConverter<RoleType, String> {
        public Converter() {
            super(RoleType.class);
        }
    }
}
