package com.ktds.esign.common.enums;

import com.ktds.esign.common.enums.converter.EnumConverter;
import com.ktds.esign.common.enums.converter.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum UserType implements IEnum<String> {
    STAFF("STAFF", "임직원"),
    PARTNER("PARTNER", "협력사");

    private String code; // db_code
    private String desc; // db_description

    // code -> ENUM TYPE
    public static UserType getTypeFromCode(String val) {
        return Arrays.stream(values()).filter(e -> e.getCode().equals(val)).findAny().orElse(null);
    }

    // desc -> ENUM TYPE
    public static UserType getTypeFromDesc(String val) {
        return Arrays.stream(values()).filter(e -> e.getDesc().equals(val)).findAny().orElse(null);
    }

    // ENUM TYPE -> code
    public static String getCodeFromType(UserType type) {
        return Optional.ofNullable(type).isPresent() ? type.getCode() : null;
    }

    // ENUM TYPE -> desc
    public static String getDescFromType(UserType type) {
        return Optional.ofNullable(type).isPresent() ? type.getDesc() : null;
    }

    public static class Converter extends EnumConverter<UserType, String> {
        public Converter() {
            super(UserType.class);
        }
    }
}