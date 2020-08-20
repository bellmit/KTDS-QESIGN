package com.ktds.esign.client.user.repository;

import com.ktds.esign.client.user.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
