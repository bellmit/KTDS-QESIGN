package com.ktds.esign.common.enums;

import com.ktds.esign.common.enums.converter.EnumConverter;
import com.ktds.esign.common.enums.converter.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 진행 상태
 */
@Getter
@AllArgsConstructor
public enum ProgsSttusType implements IEnum<String> {
    STANDBY("STANDBY", "대기"),
    ONGOING("ONGOING", "진행 중"),
    COMPLETE("COMPLETE", "완료"),
    CANCEL("CANCEL", "회수");

    private String code; // db_code
    private String desc; // db_description

    // code -> ENUM TYPE
    public static ProgsSttusType getTypeFromCode(String code) {
        return Arrays.stream(values()).filter(e -> e.getCode().equals(code)).findAny().orElse(null);
    }

    // desc -> ENUM TYPE
    public static ProgsSttusType getTypeFromDesc(String desc) {
        return Arrays.stream(values()).filter(e -> e.getDesc().equals(desc)).findAny().orElse(null);
    }

    // ENUM TYPE -> code
    public static String getCodeFromType(ProgsSttusType type) {
        return Optional.ofNullable(type).isPresent() ? type.getCode() : null;
    }

    // ENUM TYPE -> desc
    public static String getDescFromType(ProgsSttusType type) {
        return Optional.ofNullable(type).isPresent() ? type.getDesc() : null;
    }

    public static class Converter extends EnumConverter<ProgsSttusType, String> {
        public Converter() {
            super(ProgsSttusType.class);
        }
    }

}
