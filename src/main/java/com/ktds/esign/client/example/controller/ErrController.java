package com.ktds.esign.client.example.controller;

import com.ktds.esign.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/example/err")
public class ErrController {

    @GetMapping
    public ModelAndView video(ModelAndView mav) {
        mav.setViewName("views/example/err");
        return mav;
    }

    @GetMapping("make")
    public ResponseDto<?> makeError() {
        int i = 10000;
        int j = 0;
        int k = i / j;
        return ResponseDto.ok();
    }

}
