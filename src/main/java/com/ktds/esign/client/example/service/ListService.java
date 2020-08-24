package com.ktds.esign.client.example.service;

import com.ktds.esign.client.example.domain.ExUserPledge;
import com.ktds.esign.client.example.mapper.UserPledgeMapper;
import com.ktds.esign.client.example.payload.ListReq;
import com.ktds.esign.client.example.payload.ListReq.SearchDto;
import com.ktds.esign.client.example.payload.ListRes.ExUserPledgeDto;
import com.ktds.esign.client.example.repository.ListQuerydslRepository;
import com.ktds.esign.client.example.repository.ListRepository;
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
public class ListService {

    private final ListRepository listRepository;
    private final UserPledgeMapper userPledgeMapper;
    private final ListQuerydslRepository listQuerydslRepository;

    public Page<ExUserPledgeDto> findUserPledgeList(SearchDto searchDto, Pageable pageable) {
        return listQuerydslRepository.selectUserPledgeList(searchDto, pageable).map(userPledgeMapper::toDto);
    }

}
