package com.ktds.esign.client.user.repository;

import com.ktds.esign.client.user.domain.User;
import com.ktds.esign.common.querydsl.Querydsl4RepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class UserQuerydslRepository  extends Querydsl4RepositorySupport {

    public UserQuerydslRepository() {
        super(User.class);
    }

}
