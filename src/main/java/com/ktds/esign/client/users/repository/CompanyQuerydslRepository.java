package com.ktds.esign.client.users.repository;

import com.ktds.esign.client.users.domain.Company;
import com.ktds.esign.common.querydsl.Querydsl4RepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyQuerydslRepository extends Querydsl4RepositorySupport {

    public CompanyQuerydslRepository() {
        super(Company.class);
    }


}
