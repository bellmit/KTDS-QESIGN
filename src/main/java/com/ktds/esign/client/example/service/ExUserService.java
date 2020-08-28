package com.ktds.esign.client.example.service;

import com.ktds.esign.client.example.repository.ExUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ExUserService {
    private ExUserRepository exUserRepository;

}
