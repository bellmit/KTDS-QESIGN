package com.ktds.esign.client.example;

import com.ktds.esign.common.enums.UserPledgeStatusType;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

class EnumTest {

    private static final Logger log = LoggerFactory.getLogger(EnumTest.class);

    @Test
    void runEnumTest() {
        /**
         * 전달된 string value로 enum type 찾기
         */
        UserPledgeStatusType type = UserPledgeStatusType.getCode("proceeding");
        log.info("@type=================>{}", type);
        assertThat(type).isEqualTo(UserPledgeStatusType.PROCEEDING);

//        UserPledgeStatusType type2 = UserPledgeStatusType.getDesc("진행 중");
//        log.info("@type2================>{}", type2);
//        assertThat(type2).isEqualTo(UserPledgeStatusType.PROCEEDING);

        /**
         * enum Type으로 부터 특정 value 추출
         */
        String code = UserPledgeStatusType.getCodeFromType(UserPledgeStatusType.COMPLETE);
        log.info("@code=================>{}", code);
        assertThat(code).isEqualTo("complete");

        String desc = UserPledgeStatusType.getDescFromType(UserPledgeStatusType.COMPLETE);
        log.info("@desc=================>{}", desc);
        assertThat(desc).isEqualTo("완료");
    }
}
