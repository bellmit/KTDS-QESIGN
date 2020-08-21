package com.ktds.esign.client.example.repository;

import com.ktds.esign.client.example.domain.ExUserPledge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<ExUserPledge, Long> {

}
