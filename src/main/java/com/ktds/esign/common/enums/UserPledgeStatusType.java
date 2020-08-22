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
public enum UserPledgeStatusType implements IEnum<String> {
    PROCEEDING("proceeding", "진행 중"),
    COMPLETE("complete", "완료"),
    STANDBY("standby", "대기");

    private String code;
    private String Desc;

    // code -> ENUM TYPE
    public static UserPledgeStatusType getCode(String val){
        return Arrays.stream(values()).filter(e -> e.getCode().equals(val)).findAny().orElse(null);
    }

    // desc -> ENUM TYPE
    public static UserPledgeStatusType getDesc(String val){
        return Arrays.stream(values()).filter(e -> e.getDesc().equals(val)).findAny().orElse(null);
    }

    // ENUM TYPE -> code
    public static String getCodeFromType(UserPledgeStatusType type){
        return Optional.ofNullable(type).isPresent() ? String.valueOf(type.getCode()) : null;
    }

    // ENUM TYPE -> desc
    public static String getDescFromType(UserPledgeStatusType type){
        return Optional.ofNullable(type).isPresent() ? String.valueOf(type.getDesc()) : null;
    }

    public static class Converter extends EnumConverter<UserPledgeStatusType, String> {
        public Converter (){
            super(UserPledgeStatusType.class);
        }
    }

}
