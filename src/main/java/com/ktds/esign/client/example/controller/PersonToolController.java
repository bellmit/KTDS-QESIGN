package com.ktds.esign.client.example.controller;


import com.ktds.esign.client.example.payload.ExUserNotiReq;
import com.ktds.esign.client.example.payload.ExUserNotiReq.CreateDto;
import com.ktds.esign.client.example.payload.ExUserPledgeReq;
import com.ktds.esign.client.example.service.ExUserNotiService;
import com.ktds.esign.code.service.CodeService;
import com.ktds.esign.common.exception.UserNotFoundException;
import com.ktds.esign.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("example")
public class PersonToolController {

    private final CodeService codeService;
    private final ExUserNotiService userNotiService;

    @GetMapping("person-tool/view")
    public ModelAndView pledge(ModelAndView mav) {
        mav.setViewName("views/example/person-tool");
        mav.addObject("commonCode", codeService.findAllCodeList());
        return mav;
    }

    @PostMapping("person-tool")
    public ResponseDto<?> createUserNoti(@RequestBody CreateDto createDto) throws UserNotFoundException {
        userNotiService.createUserNoti(createDto);
        return ResponseDto.ok();
    }

}
