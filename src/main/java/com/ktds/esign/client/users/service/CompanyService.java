package com.ktds.esign.client.users.service;

import com.ktds.esign.client.users.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

}
