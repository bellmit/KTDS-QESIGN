package com.ktds.esign.client.example.repository;

import com.ktds.esign.client.example.domain.ExUserPledge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListRepository extends JpaRepository<ExUserPledge, Long> {
    Page<ExUserPledge> findAllByOrderByStartDtAscUserPledgeStatusDesc(Pageable pageable);
    List<ExUserPledge> findAllByOrderByStartDtDesc();
}
