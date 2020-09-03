package com.ktds.esign.common.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println(ex.getClass() + " : " + ex.getMessage());

        if (ex instanceof MaxUploadSizeExceededException) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("/errors/error");
            mav.addObject("message", ex.getMessage());
            mav.addObject("statusCode", HttpStatus.BAD_REQUEST.value());
            return mav;
        }
        return null;
    }
}

