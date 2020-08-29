package com.ktds.esign.client.users.repository;

import com.ktds.esign.client.users.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
