package com.ktds.esign.client.user.repository;

import com.ktds.esign.client.user.domain.Department;
import com.ktds.esign.common.querydsl.Querydsl4RepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentQuerydslRepository extends Querydsl4RepositorySupport {

    public DepartmentQuerydslRepository() {
        super(Department.class);
    }

}

