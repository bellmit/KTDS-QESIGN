package com.ktds.esign.client.example.service;

import com.ktds.esign.client.example.mapper.UserPledgeMapper;
import com.ktds.esign.client.example.payload.ListReq;
import com.ktds.esign.client.example.payload.ListReq.ExUserPledgeDto;
import com.ktds.esign.client.example.repository.ListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ListService {

    private final ListRepository listRepository;
    private final UserPledgeMapper userPledgeMapper;

    public List<ExUserPledgeDto> findUserPledges() {
        return userPledgeMapper.toDtoList(listRepository.findAll());
    }

}
