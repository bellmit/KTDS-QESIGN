package com.ktds.esign.code.service;

import com.ktds.esign.code.domain.Code;
import com.ktds.esign.code.mapper.CodeMapper;
import com.ktds.esign.code.payload.CodeRes.CodeDto;
import com.ktds.esign.code.repository.CodeRepository;
import com.ktds.esign.common.exception.CodeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CodeService {

    private final CodeRepository codeRepository;
    private final CodeMapper codeMapper;

    public List<CodeDto> findAllCodeList() {
        return codeMapper.toDtoList(codeRepository.findAllCodes());
    }

    public List<CodeDto> findCodeByGroupCode(String groupCode) {
        return codeMapper.toDtoList(codeRepository.findCodesByGroupCodeId(groupCode));
    }

    public CodeDto findCodeByGroupCodeAndCode(String groupCodeId, String codeId) throws CodeNotFoundException {
        Code code = codeRepository.findCodeByGroupCodeIdAndCodeId(groupCodeId, codeId)
                .orElseThrow(CodeNotFoundException::new);
        return codeMapper.toDto(code);
    }

}
