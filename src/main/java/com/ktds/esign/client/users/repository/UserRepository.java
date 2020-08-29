package com.ktds.esign.client.users.repository;

import com.ktds.esign.client.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
