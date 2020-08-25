package com.ktds.esign.client.example;

import com.ktds.esign.client.example.domain.ExUserPledge;
import com.ktds.esign.client.example.mapper.UserPledgeMapper;
import com.ktds.esign.client.example.payload.ExUserPledgeReq;
import com.ktds.esign.client.example.payload.ExUserPledgeRes.ExUserPledgeDto;
import com.ktds.esign.client.example.repository.ExPledgeRepository;
import com.ktds.esign.common.enums.PledgeType;
import com.ktds.esign.common.enums.PledgeAcceptType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 2020.08.23
 */
@SpringBootTest
class EnumRepoTest {

    private static final Logger log = LoggerFactory.getLogger(EnumRepoTest.class);

    @Autowired
    private ExPledgeRepository listRepository;

    @Autowired
    private UserPledgeMapper userPledgeMapper;

    @Transactional // for rollback
    @Test
    @DisplayName("enum 컨버터를 이용해 enum 필드 저장 시 코드 값으로 치환 저장 테스트")
    void runEnumSaveTest() {
        // front ======> backend ========> database
        // string <=====> enum <===========> string
        ExUserPledgeReq.CreateDto createDto = ExUserPledgeReq.CreateDto.builder()
                .pledgeName("우리는 지금 제네바로 간다.")
                .pledgeAcceptType("STANDBY")
                .pledgeType("VIDEO")
                .startDt(LocalDateTime.of(2015, 4, 28, 00, 00, 00))
                .endDt(LocalDateTime.of(2020, 9, 12, 00, 00, 00))
                .reqUser("James Bond")
                .reqDept("MI6")
                .build();

        log.info("@created dto==================>{}", createDto);
        ExUserPledge exUserPledge = userPledgeMapper.createEntity(createDto);
        log.info("@mapping entity===============>{}", exUserPledge);
        assertThat(exUserPledge.getPledgeAcceptType()).isEqualTo(PledgeAcceptType.STANDBY);
        assertThat(exUserPledge.getPledgeType()).isEqualTo(PledgeType.VIDEO);
        listRepository.save(exUserPledge);
        assertThat(exUserPledge.getId()).isNotNull();
    }

    @Test
    @DisplayName("enum 컨버터를 이용해 조회 시 enum 필드 가 코드 값에서 enum으로 치환 조회 테스트")
    void runEnumSearchTest() {
        String username = "Doom Fist";
        ExUserPledge exUserPledge = listRepository.findByReqUser(username).get();
        log.info("@search.entity===============>{}", exUserPledge);
        assertThat(exUserPledge).isNotNull();
        assertThat(exUserPledge.getPledgeAcceptType()).isEqualTo(PledgeAcceptType.COMPLETE);

        ExUserPledgeDto exUserPledgeDto = userPledgeMapper.toDto(exUserPledge);
        log.info("@mapping.dto=================>{}", exUserPledgeDto);
        assertThat(exUserPledgeDto).isNotNull();
        assertThat(exUserPledgeDto.getPledgeAcceptType()).isEqualTo("COMPLETE");
        assertThat(exUserPledgeDto.getPledgeType()).isEqualTo("HTML");
    }

}
