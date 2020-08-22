package com.ktds.esign.common.enums;

import com.ktds.esign.common.enums.converter.EnumConverter;
import com.ktds.esign.common.enums.converter.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 서약 업무 진행 상태
 */
@Getter
@AllArgsConstructor
public enum PledgeProgType implements IEnum<String> {
    PROCEED("PROCEED", "진행 중"),
    COMPLETE("COMPLETE", "완료"),
    STANDBY("STANDBY", "대기"),
    DELETE("DELETE", "삭제"),
    CANCEL("CANCEL", "취소");

    private String code; // db_code
    private String Desc; // db_description

    // code -> ENUM TYPE
    public static PledgeProgType getTypeFromCode(String code) {
        return Arrays.stream(values()).filter(e -> e.getCode().equals(code)).findAny().orElse(null);
    }

    // desc -> ENUM TYPE
    public static PledgeProgType getTypeFromDesc(String desc) {
        return Arrays.stream(values()).filter(e -> e.getDesc().equals(desc)).findAny().orElse(null);
    }

    // ENUM TYPE -> code
    public static String getCodeFromType(PledgeProgType type) {
        return Optional.ofNullable(type).isPresent() ? type.getCode() : null;
    }

    // ENUM TYPE -> desc
    public static String getDescFromType(PledgeProgType type) {
        return Optional.ofNullable(type).isPresent() ? type.getDesc() : null;
    }

    public static class Converter extends EnumConverter<PledgeProgType, String> {
        public Converter() {
            super(PledgeProgType.class);
        }
    }

}
