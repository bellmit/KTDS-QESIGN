package com.ktds.esign.common.enums.formatter;

import com.ktds.esign.common.annos.enums.*;
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

    @PledgeProgTypeMapper
    public static PledgeAcceptType getPledgeProgType(String code) {
        return PledgeAcceptType.getTypeFromCode(code);
    }

    @PledgeProgCodeMapper
    public static String getPledgeProgCode(PledgeAcceptType type) {
        return PledgeAcceptType.getCodeFromType(type);
    }

    @PledgeTypeMapper
    public static PledgeType getPledgeType(String code) {
        return PledgeType.getTypeFromCode(code);
    }

    @PledgeCodeMapper
    public static String getPledgeCode(PledgeType type) {
        return PledgeType.getCodeFromType(type);
    }

}
