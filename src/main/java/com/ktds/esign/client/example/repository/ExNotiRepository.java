package com.ktds.esign.client.example.repository;

import com.ktds.esign.client.example.domain.ExNoti;
import com.ktds.esign.common.enums.NotiType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExNotiRepository extends JpaRepository<ExNoti, Long> {
    ExNoti findByNotiType(NotiType typeFromCode);
}
