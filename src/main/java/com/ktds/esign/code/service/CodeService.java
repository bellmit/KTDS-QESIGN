package com.ktds.esign.code.service;

import com.ktds.esign.code.domain.CommonCode;
import com.ktds.esign.code.mapper.CodeMapper;
import com.ktds.esign.code.payload.CodeRes;
import com.ktds.esign.code.payload.CodeRes.CommonCodeDto;
import com.ktds.esign.code.repository.CodeRepository;
import com.ktds.esign.common.exception.CodeNotFoundException;
import com.ktds.esign.common.exception.ResourceNotFoundException;
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

    public List<CommonCodeDto> findAllCodeList() {
        return codeMapper.toDtoList(codeRepository.findCodeByOrderByGroupCode());
    }

    public List<CommonCodeDto> findCodeByGroupCode(String groupCode) {
        return codeMapper.toDtoList(codeRepository.findCodeByGroupCode(groupCode));
    }

    public CommonCodeDto findCodeByGroupCodeAndCode(String groupCode, String code) throws CodeNotFoundException {
        CommonCode commonCode = codeRepository.findCodeByGroupCodeAndCode(groupCode, code)
                .orElseThrow(CodeNotFoundException::new);
        return codeMapper.toDto(commonCode);
    }

}
