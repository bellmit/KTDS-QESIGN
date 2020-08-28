package com.ktds.esign.client.example.repository;

import com.ktds.esign.client.example.domain.ExUserPledge;
import com.ktds.esign.common.enums.ProgsSttusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ExPledgeRepository extends JpaRepository<ExUserPledge, Long> {
    Page<ExUserPledge> findAllByOrderByStartDtAscProgsSttusTypeDesc(Pageable pageable);

    List<ExUserPledge> findAllByOrderByStartDtDesc();

    Optional<ExUserPledge> findByReqUser(String user);

    Long countByEndDtBetween(LocalDateTime startDt, LocalDateTime endDt);

    Long countByProgsSttusTypeAndEndDtBetween(ProgsSttusType progsSttusType, LocalDateTime startDt, LocalDateTime endDt);

    Long countByStartDtBetween(LocalDateTime startDt, LocalDateTime endDt);

    Long countByProgsSttusTypeAndStartDtBetween(ProgsSttusType progsSttusType, LocalDateTime startDt, LocalDateTime endDt);
}
