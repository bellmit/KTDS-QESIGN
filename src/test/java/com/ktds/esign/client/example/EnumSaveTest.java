package com.ktds.esign.client.example;

import com.ktds.esign.client.example.domain.ExUserPledge;
import com.ktds.esign.client.example.mapper.UserPledgeMapper;
import com.ktds.esign.client.example.payload.ListReq;
import com.ktds.esign.client.example.payload.ListRes.ExUserPledgeDto;
import com.ktds.esign.client.example.repository.ListRepository;
import com.ktds.esign.common.enums.PledgeType;
import com.ktds.esign.common.enums.PledgeAcceptType;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EnumSaveTest {

    private static final Logger log = LoggerFactory.getLogger(EnumSaveTest.class);

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private UserPledgeMapper userPledgeMapper;

    @Test
    // @Transactional // for rollback
    void runEnumSaveTest() {
        // front ======> backend ========> database
        // string <=====> enum <===========> string
        ListReq.CreateDto createDto = ListReq.CreateDto.builder()
                .pledgeName("우리는 지금 제네바로 간다.")
                .userPledgeStatus("STANDBY")
                .pledgeType("VIDEO")
                .startDt(LocalDateTime.of(2015, 4, 28, 00, 00, 00))
                .endDt(LocalDateTime.of(2020, 9, 12, 00, 00, 00))
                .reqUser("James Bond")
                .reqDept("MI6")
                .build();

        log.info("@created dto==================>{}", createDto);
        ExUserPledge exUserPledge = userPledgeMapper.toEntity(createDto);
        log.info("@mapping entity===============>{}", exUserPledge);
        assertThat(exUserPledge.getUserPledgeStatus()).isEqualTo(PledgeAcceptType.STANDBY);
        assertThat(exUserPledge.getPledgeType()).isEqualTo(PledgeType.VIDEO);
        listRepository.save(exUserPledge);
        assertThat(exUserPledge.getId()).isNotNull();

    }

    @Test
    void runEnumSearchTest() {
        String username = "James Bond";
        ExUserPledge exUserPledge = listRepository.findByReqUser(username).get();
        log.info("@search.entity===============>{}", exUserPledge);
        assertThat(exUserPledge).isNotNull();
        assertThat(exUserPledge.getUserPledgeStatus()).isEqualTo(PledgeAcceptType.STANDBY);

        ExUserPledgeDto exUserPledgeDto = userPledgeMapper.toDto(exUserPledge);
        log.info("@mapping.dto=================>{}", exUserPledgeDto);
        assertThat(exUserPledgeDto).isNotNull();
        assertThat(exUserPledgeDto.getUserPledgeStatus()).isEqualTo("STANDBY");
        assertThat(exUserPledgeDto.getPledgeType()).isEqualTo("VIDEO");
    }

}
