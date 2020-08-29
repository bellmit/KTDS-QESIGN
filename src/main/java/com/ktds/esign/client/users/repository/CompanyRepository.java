package com.ktds.esign.client.users.repository;

import com.ktds.esign.client.users.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
