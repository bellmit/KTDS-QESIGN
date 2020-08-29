package com.ktds.esign.client.users.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    /**
     * 개인 설정 화면 이동
     *
     * @param mav
     * @return
     */
    @GetMapping("personal-setting")
    public ModelAndView personalSettingView(ModelAndView mav) {
        mav.setViewName("views/users/personal-setting");
        return mav;
    }

}
