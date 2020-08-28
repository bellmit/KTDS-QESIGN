package com.ktds.esign.common.enums;

import com.ktds.esign.common.enums.converter.EnumConverter;
import com.ktds.esign.common.enums.converter.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum NotiDirectType implements IEnum<String> {
    SEND("SEND", "수신 메시지 설정"),
    RECEIVE("RECEIVE", "송신 메시지 설정");

    private String code; // db_code
    private String desc; // db_description

    // code -> ENUM TYPE
    public static NotiDirectType getTypeFromCode(String code) {
        return Arrays.stream(values()).filter(e -> e.getCode().equals(code)).findAny().orElseThrow(IllegalArgumentException::new);
    }

    // desc -> ENUM TYPE
    public static NotiDirectType getTypeFromDesc(String desc) {
        return Arrays.stream(values()).filter(e -> e.getDesc().equals(desc)).findAny().orElseThrow(IllegalArgumentException::new);
    }

    // ENUM TYPE -> code
    public static String getCodeFromType(NotiDirectType type) {
        return Optional.ofNullable(type).isPresent() ? type.getCode() : null;
    }

    // ENUM TYPE -> desc
    public static String getDescFromType(NotiDirectType type) {
        return Optional.ofNullable(type).isPresent() ? type.getDesc() : null;
    }

    public static class Converter extends EnumConverter<NotiDirectType, String> {
        public Converter() {
            super(NotiDirectType.class);
        }
    }
}

