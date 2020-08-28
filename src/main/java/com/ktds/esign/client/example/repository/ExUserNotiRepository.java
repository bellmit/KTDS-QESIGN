package com.ktds.esign.client.example.repository;

import com.ktds.esign.client.example.domain.ExUser;
import com.ktds.esign.client.example.domain.ExUserNoti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExUserNotiRepository extends JpaRepository<ExUserNoti, Long> {
    ExUserNoti findByUser(ExUser user);
    void deleteByUser(ExUser user);
}
