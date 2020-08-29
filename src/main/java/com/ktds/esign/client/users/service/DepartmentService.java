package com.ktds.esign.client.users.service;

import com.ktds.esign.client.users.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

}
