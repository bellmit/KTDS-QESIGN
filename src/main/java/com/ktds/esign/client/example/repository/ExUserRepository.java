package com.ktds.esign.client.example.repository;

import com.ktds.esign.client.example.domain.ExUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExUserRepository extends JpaRepository<ExUser, String> {
}
