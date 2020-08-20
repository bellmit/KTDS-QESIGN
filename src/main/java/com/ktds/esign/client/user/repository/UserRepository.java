package com.ktds.esign.client.user.repository;

import com.ktds.esign.client.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
