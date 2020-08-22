package com.ktds.esign.common.enums.formatter;

import com.ktds.esign.common.annos.enums.*;
import com.ktds.esign.common.enums.PledgeType;
import com.ktds.esign.common.enums.PledgeAcceptType;
import org.springframework.stereotype.Component;

/**
 * DTO to Entity Enum Formatter
 */
@Component
@RootMapper
public class EnumFormatter {

    @UserPledgeStatusTypeMapper
    public static PledgeAcceptType getUserPledgeStatusType(String code) {
        return PledgeAcceptType.getTypeFromCode(code);
    }

    @UserPledgeStatusCodeMapper
    public static String getUserPledgeStatusCode(PledgeAcceptType type) {
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
