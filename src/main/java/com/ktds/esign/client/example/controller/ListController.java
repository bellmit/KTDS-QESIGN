package com.ktds.esign.client.example.controller;

import com.ktds.esign.client.example.payload.ListReq.ExUserPledgeDto;
import com.ktds.esign.client.example.service.ListService;
import com.ktds.esign.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("example/list")
public class ListController {

    private final ListService listService;

    @GetMapping
    public ModelAndView video(ModelAndView mav) {
        mav.setViewName("views/example/list");
        return mav;
    }

    @GetMapping(path = "userpledge")
    public ResponseDto<?> pledgeUser() {
        return ResponseDto.of(listService.findUserPledges());
    }

}
