package com.ktds.esign.common.enums.format;

import com.ktds.esign.common.annos.UserPledgeFomatter;
import com.ktds.esign.common.enums.UserPledgeStatusType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

public class EnumFomatter {

    @UserPledgeFomatter
    public static UserPledgeStatusType getCode(String val) {
        return Arrays.stream(UserPledgeStatusType.values()).filter(e -> e.getCode().equals(val)).findAny().orElse(null);
    }

}
