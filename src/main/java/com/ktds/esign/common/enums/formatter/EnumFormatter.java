package com.ktds.esign.common.enums.formatter;

import com.ktds.esign.common.annos.enums.*;
import com.ktds.esign.common.enums.PledgeProgType;
import com.ktds.esign.common.enums.PledgeType;
import com.ktds.esign.common.enums.PledgeAcceptType;
import org.springframework.stereotype.Component;

/**
 * DTO to Entity Enum Type Mapping
 * Entity to DTO Enum Code Mapping
 */
@Component
@RootEnumMapper
public class EnumFormatter {

    /**
     * enum: PledgeAcceptType
     */
    @PledgeAcceptTypeMapper // String(code) -> Enum(type)
    public static PledgeAcceptType getPledgeAcceptType(String code) {
        return PledgeAcceptType.getTypeFromCode(code);
    }

    @PledgeAcceptCodeMapper // Enum(type) -> String(code)
    public static String getPledgeAcceptCode(PledgeAcceptType type) {
        return PledgeAcceptType.getCodeFromType(type);
    }

    @PledgeAcceptDescMapper // Enum(type) -> String(desc)
    public static String getPledgeAcceptDesc(PledgeAcceptType type) {
        return PledgeAcceptType.getDescFromType(type);
    }

    /**
     * enum: PledgeProgType
     */
    @PledgeProgTypeMapper // String(code) -> Enum(type)
    public static PledgeProgType getPledgeProgType(String code) {
        return PledgeProgType.getTypeFromCode(code);
    }

    @PledgeProgCodeMapper // Enum(type) -> String(code)
    public static String getPledgeProgCode(PledgeProgType type) {
        return PledgeProgType.getCodeFromType(type);
    }

    @PledgeProgDescMapper // Enum(type) -> String(code)
    public static String getPledgeProgDesc(PledgeProgType type) {
        return PledgeProgType.getDescFromType(type);
    }

    /**
     * enum: PledgeType
     */
    @PledgeTypeMapper // String(code) -> Enum(type)
    public static PledgeType getPledgeType(String code) {
        return PledgeType.getTypeFromCode(code);
    }

    @PledgeCodeMapper // Enum(type) -> String(code)
    public static String getPledgeCode(PledgeType type) {
        return PledgeType.getCodeFromType(type);
    }

    @PledgeDescMapper // Enum(type) -> String(code)
    public static String getPledgeDesc(PledgeType type) {
        return PledgeType.getDescFromType(type);
    }

}
