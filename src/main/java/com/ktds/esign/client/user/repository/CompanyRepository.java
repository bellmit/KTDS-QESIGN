package com.ktds.esign.client.user.repository;

import com.ktds.esign.client.user.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
