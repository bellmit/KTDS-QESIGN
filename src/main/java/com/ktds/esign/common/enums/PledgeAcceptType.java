package com.ktds.esign.common.enums;

import com.ktds.esign.common.enums.converter.EnumConverter;
import com.ktds.esign.common.enums.converter.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 서약 대상자의 서약 상태
 */
@Getter
@AllArgsConstructor
public enum PledgeAcceptType implements IEnum<String> {
    STANDBY("STANDBY", "대기"),
    PROCEEDING("PROCEEDING", "진행 중"),
    COMPLETE("COMPLETE", "완료");

    private String code; // db_code
    private String Desc; // db_description

    // code -> ENUM TYPE
    public static PledgeAcceptType getTypeFromCode(String code) {
        return Arrays.stream(values()).filter(e -> e.getCode().equals(code)).findAny().orElse(null);
    }

    // desc -> ENUM TYPE
    public static PledgeAcceptType getTypeFromDesc(String desc) {
        return Arrays.stream(values()).filter(e -> e.getDesc().equals(desc)).findAny().orElse(null);
    }

    // ENUM TYPE -> code
    public static String getCodeFromType(PledgeAcceptType type) {
        return Optional.ofNullable(type).isPresent() ? type.getCode() : null;
    }

    // ENUM TYPE -> desc
    public static String getDescFromType(PledgeAcceptType type) {
        return Optional.ofNullable(type).isPresent() ? type.getDesc() : null;
    }

    public static class Converter extends EnumConverter<PledgeAcceptType, String> {
        public Converter() {
            super(PledgeAcceptType.class);
        }
    }

}
