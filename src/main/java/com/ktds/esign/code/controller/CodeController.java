package com.ktds.esign.code.controller;

import com.ktds.esign.code.service.CodeService;
import com.ktds.esign.common.exception.CodeNotFoundException;
import com.ktds.esign.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/commoncodes")
public class CodeController {

    private final CodeService codeService;

    @GetMapping()
    public ResponseDto<?> getAllCodeList() {
        return ResponseDto.of(codeService.findAllCodeList());
    }

    @GetMapping("groupcode/{groupCodeId}")
    public ResponseDto<?> getCodeListByGroupCode(@PathVariable("groupCodeId") String groupCodeId) {
        return ResponseDto.of(codeService.findCodeByGroupCode(groupCodeId));
    }

    @GetMapping("groupcode/{groupCodeId}/code/{codeId}")
    public ResponseDto<?> getCodeByGroupCodeAndCode(@PathVariable("groupCodeId") String groupCodeId, @PathVariable("codeId") String codeId) throws CodeNotFoundException {
        return ResponseDto.of(codeService.findCodeByGroupCodeAndCode(groupCodeId, codeId));
    }

}
