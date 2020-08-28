package com.ktds.esign.common.enums;

import com.ktds.esign.common.enums.converter.EnumConverter;
import com.ktds.esign.common.enums.converter.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum NotiType implements IEnum<String> {
    SMS("SMS", "SMS"),
    EMAIL("EMAIL", "EMAIL"),
    PUSH("PUSH", "PUSH");

    private String code; // db_code
    private String desc; // db_description

    // code -> ENUM TYPE
    public static NotiType getTypeFromCode(String code) {
        return Arrays.stream(values()).filter(e -> e.getCode().equals(code)).findAny().orElseThrow(IllegalArgumentException::new);
    }

    // desc -> ENUM TYPE
    public static NotiType getTypeFromDesc(String desc) {
        return Arrays.stream(values()).filter(e -> e.getDesc().equals(desc)).findAny().orElseThrow(IllegalArgumentException::new);
    }

    // ENUM TYPE -> code
    public static String getCodeFromType(NotiType type) {
        return Optional.ofNullable(type).isPresent() ? type.getCode() : null;
    }

    // ENUM TYPE -> desc
    public static String getDescFromType(NotiType type) {
        return Optional.ofNullable(type).isPresent() ? type.getDesc() : null;
    }

    public static class Converter extends EnumConverter<NotiType, String> {
        public Converter() {
            super(NotiType.class);
        }
    }
}
