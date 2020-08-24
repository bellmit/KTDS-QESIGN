package com.ktds.esign.code.controller;

import com.ktds.esign.client.example.payload.ListReq;
import com.ktds.esign.client.example.service.ListService;
import com.ktds.esign.code.service.CodeService;
import com.ktds.esign.common.exception.CodeNotFoundException;
import com.ktds.esign.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("groupcode/{groupcode}")
    public ResponseDto<?> getCodeListByGroupCode(@PathVariable("groupcode") String groupCode) {
        return ResponseDto.of(codeService.findCodeByGroupCode(groupCode));
    }

    @GetMapping("groupcode/{groupcode}/code/{code}")
    public ResponseDto<?> getCodeByGroupCodeAndCode(@PathVariable("groupcode") String groupCode, @PathVariable("code") String code) throws CodeNotFoundException {
        return ResponseDto.of(codeService.findCodeByGroupCodeAndCode(groupCode, code));
    }

}
