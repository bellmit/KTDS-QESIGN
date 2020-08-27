package com.ktds.esign.common.enums;

import com.ktds.esign.common.enums.converter.EnumConverter;
import com.ktds.esign.common.enums.converter.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum DirectionType implements IEnum<String> {
    SEND("SEND", "SEND"),
    RECEIVE("RECEIVE", "RECEIVE");


    private String code; // db_code
    private String Desc; // db_description

    // code -> ENUM TYPE
    public static DirectionType getTypeFromCode(String code) {
        return Arrays.stream(values()).filter(e -> e.getCode().equals(code)).findAny().orElseThrow(IllegalArgumentException::new);
    }

    // desc -> ENUM TYPE
    public static DirectionType getTypeFromDesc(String desc) {
        return Arrays.stream(values()).filter(e -> e.getDesc().equals(desc)).findAny().orElseThrow(IllegalArgumentException::new);
    }

    // ENUM TYPE -> code
    public static String getCodeFromType(DirectionType type) {
        return Optional.ofNullable(type).isPresent() ? type.getCode() : null;
    }

    // ENUM TYPE -> desc
    public static String getDescFromType(DirectionType type) {
        return Optional.ofNullable(type).isPresent() ? type.getDesc() : null;
    }

    public static class Converter extends EnumConverter<DirectionType, String> {
        public Converter() {
            super(DirectionType.class);
        }
    }
}

