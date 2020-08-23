package com.ktds.esign.client.example;

import com.ktds.esign.common.enums.PledgeAcceptType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 2020.08.23
 */
class EnumMappingTest {

    private static final Logger log = LoggerFactory.getLogger(EnumMappingTest.class);

    @Test
    @DisplayName("mapstruct를 이용해 DTO와 Entity간의 enum type, code를 맵핑하는 테스트")
    void runEnumMappingTest() {
        /**
         * 전달된 string value로 enum type 찾기
         */
        PledgeAcceptType type = PledgeAcceptType.getTypeFromCode("PROCEEDING");
        log.info("@type=================>{}", type);
        assertThat(type).isEqualTo(PledgeAcceptType.PROCEEDING);

        PledgeAcceptType type2 = PledgeAcceptType.getTypeFromDesc("진행 중");
        log.info("@type2================>{}", type2);
        assertThat(type2).isEqualTo(PledgeAcceptType.PROCEEDING);

        /**
         * enum Type으로 부터 특정 value 추출
         */
        String code = PledgeAcceptType.getCodeFromType(PledgeAcceptType.COMPLETE);
        log.info("@code=================>{}", code);
        assertThat(code).isEqualTo("COMPLETE");

        String desc = PledgeAcceptType.getDescFromType(PledgeAcceptType.COMPLETE);
        log.info("@desc=================>{}", desc);
        assertThat(desc).isEqualTo("완료");
    }

}
