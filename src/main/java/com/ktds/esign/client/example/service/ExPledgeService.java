package com.ktds.esign.client.example.service;

import com.ktds.esign.client.example.domain.ExUserPledge;
import com.ktds.esign.client.example.mapper.UserPledgeMapper;
import com.ktds.esign.client.example.payload.ExUserPledgeReq.CreateDto;
import com.ktds.esign.client.example.payload.ExUserPledgeReq.SearchDto;
import com.ktds.esign.client.example.payload.ExUserPledgeReq.UpdateDto;
import com.ktds.esign.client.example.payload.ExUserPledgeRes;
import com.ktds.esign.client.example.payload.ExUserPledgeRes.FindDto;
import com.ktds.esign.client.example.repository.ExPledgeQuerydslRepository;
import com.ktds.esign.client.example.repository.ExPledgeRepository;
import com.ktds.esign.common.enums.PledgeAcceptType;
import com.ktds.esign.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ktds.esign.client.example.payload.ExUserPledgeRes.ResultCountDto;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ExPledgeService {

    private final ExPledgeRepository exPledgeRepository;
    private final UserPledgeMapper userPledgeMapper;
    private final ExPledgeQuerydslRepository exPledgeQuerydslRepository;

    // 생성
    @Transactional
    public Long createUserPledge(CreateDto createDto) {
        return exPledgeRepository.save(userPledgeMapper.createEntity(createDto)).getId();
    }

    // 사용자 서약서 승인 진행 카운트 조회
    public ResultCountDto findUserPledgeResultCount(SearchDto searchDto) {
        Long standbyCount;
        Long proceedingCount;
        Long completeCount;

        if (searchDto.getDateType().equals("endDt")) {
            standbyCount = exPledgeRepository.countByPledgeAcceptTypeAndEndDtBetween(PledgeAcceptType.STANDBY, searchDto.getStartDt(), searchDto.getEndDt());
            proceedingCount = exPledgeRepository.countByPledgeAcceptTypeAndEndDtBetween(PledgeAcceptType.PROCEEDING, searchDto.getStartDt(), searchDto.getEndDt());
            completeCount = exPledgeRepository.countByPledgeAcceptTypeAndEndDtBetween(PledgeAcceptType.COMPLETE, searchDto.getStartDt(), searchDto.getEndDt());
        } else {
            standbyCount = exPledgeRepository.countByPledgeAcceptTypeAndStartDtBetween(PledgeAcceptType.STANDBY, searchDto.getStartDt(), searchDto.getEndDt());
            proceedingCount = exPledgeRepository.countByPledgeAcceptTypeAndStartDtBetween(PledgeAcceptType.PROCEEDING, searchDto.getStartDt(), searchDto.getEndDt());
            completeCount = exPledgeRepository.countByPledgeAcceptTypeAndStartDtBetween(PledgeAcceptType.COMPLETE, searchDto.getStartDt(), searchDto.getEndDt());
        }
        return ResultCountDto.of(standbyCount, proceedingCount, completeCount);
    }

    // 리스트(페이징) 조회
    public Page<ExUserPledgeRes.FindDto> findUserPledgeList(SearchDto searchDto, Pageable pageable) {
        return exPledgeQuerydslRepository.selectUserPledgeList(searchDto, pageable).map(userPledgeMapper::toDto);
    }

    // 단건 조회
    public FindDto findUserPledgeDetail(Long id) throws ResourceNotFoundException {
        return userPledgeMapper.toDto(exPledgeRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    // 수정
    @Transactional
    public Long updateUserPledge(Long id, UpdateDto updateDto) throws ResourceNotFoundException {
        ExUserPledge exUserPledge = exPledgeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        userPledgeMapper.updateEntity(updateDto, exUserPledge);
        return exUserPledge.getId();
    }

    // 삭제
    @Transactional
    public void deleteUserPledge(Long id) {
        exPledgeRepository.deleteById(id);
    }

}
