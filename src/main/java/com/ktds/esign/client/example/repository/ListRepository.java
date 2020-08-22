package com.ktds.esign.client.example.repository;

import com.ktds.esign.client.example.domain.ExUserPledge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ListRepository extends JpaRepository<ExUserPledge, Long> {
    Page<ExUserPledge> findAllByOrderByStartDtAscUserPledgeStatusDesc(Pageable pageable);
    List<ExUserPledge> findAllByOrderByStartDtDesc();
    Optional<ExUserPledge> findByReqUser(String user);
}
