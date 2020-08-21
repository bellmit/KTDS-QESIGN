package com.ktds.esign.client.example.repository;

import com.ktds.esign.client.example.domain.ExBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditorRepository extends JpaRepository<ExBoard, Long> {
}
