package com.ktds.esign.client.example.repository;

import com.ktds.esign.client.example.domain.ExUserPledge;
import com.ktds.esign.common.enums.PledgeAcceptType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ExPledgeRepository extends JpaRepository<ExUserPledge, Long> {
    Page<ExUserPledge> findAllByOrderByStartDtAscPledgeAcceptTypeDesc(Pageable pageable);
    List<ExUserPledge> findAllByOrderByStartDtDesc();
    Optional<ExUserPledge> findByReqUser(String user);
    Long countByEndDtBetween(LocalDateTime startDt, LocalDateTime endDt);
    Long countByPledgeAcceptTypeAndEndDtBetween(PledgeAcceptType standby, LocalDateTime startDt, LocalDateTime endDt);
    Long countByStartDtBetween(LocalDateTime startDt, LocalDateTime endDt);
    Long countByPledgeAcceptTypeAndStartDtBetween(PledgeAcceptType proceeding, LocalDateTime startDt, LocalDateTime endDt);
}
