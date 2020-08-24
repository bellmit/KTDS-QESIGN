package com.ktds.esign.client.example.service;

import com.ktds.esign.client.example.mapper.UserPledgeMapper;
import com.ktds.esign.client.example.payload.ExUserPledgeReq.SearchDto;
import com.ktds.esign.client.example.payload.ExUserPledgeRes.ExUserPledgeDto;
import com.ktds.esign.client.example.repository.ExPledgeQuerydslRepository;
import com.ktds.esign.client.example.repository.ExPledgeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ExPledgeService {

    private final ExPledgeRepository exPledgeRepository;
    private final UserPledgeMapper userPledgeMapper;
    private final ExPledgeQuerydslRepository exPledgeQuerydslRepository;

    public Page<ExUserPledgeDto> findUserPledgeList(SearchDto searchDto, Pageable pageable) {
        return exPledgeQuerydslRepository.selectUserPledgeList(searchDto, pageable).map(userPledgeMapper::toDto);
    }

}
