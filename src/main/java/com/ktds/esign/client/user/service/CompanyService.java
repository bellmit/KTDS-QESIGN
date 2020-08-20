package com.ktds.esign.client.user.service;

import com.ktds.esign.client.user.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

}
