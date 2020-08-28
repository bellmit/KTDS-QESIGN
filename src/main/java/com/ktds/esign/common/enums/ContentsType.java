package com.ktds.esign.common.enums;

import com.ktds.esign.common.enums.converter.EnumConverter;
import com.ktds.esign.common.enums.converter.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum ContentsType implements IEnum<String> {
    HTML("HTML", "일반"),
    VIDEO("VIDEO", "동영상");

    private String code; // db_code
    private String desc; // db_description

    // code -> ENUM TYPE
    public static ContentsType getTypeFromCode(String val) {
        return Arrays.stream(values()).filter(e -> e.getCode().equals(val)).findAny().orElse(null);
    }

    // desc -> ENUM TYPE
    public static ContentsType getTypeFromDesc(String val) {
        return Arrays.stream(values()).filter(e -> e.getDesc().equals(val)).findAny().orElse(null);
    }

    // ENUM TYPE -> code
    public static String getCodeFromType(ContentsType type) {
        return Optional.ofNullable(type).isPresent() ? type.getCode() : null;
    }

    // ENUM TYPE -> desc
    public static String getDescFromType(ContentsType type) {
        return Optional.ofNullable(type).isPresent() ? type.getDesc() : null;
    }

    public static class Converter extends EnumConverter<ContentsType, String> {
        public Converter() {
            super(ContentsType.class);
        }
    }

}
