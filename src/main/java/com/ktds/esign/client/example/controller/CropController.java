package com.ktds.esign.client.example.controller;

import com.ktds.esign.client.example.payload.CropFileDto;
import com.ktds.esign.common.response.ResponseDto;
import com.ktds.esign.common.utils.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/example/crop")
public class CropController {

    private final CommonUtil commonUtil;

    @Value("${app.file.upload.path}")
    private String fileUploadPath;

    @GetMapping
    public ModelAndView video(ModelAndView mav) {
        mav.setViewName("views/example/crop");
        return mav;
    }

    @PostMapping(value = "/upload/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto<?> saveRoute(@ModelAttribute final CropFileDto cropFileDto) {
        log.info("@crop/upload===========================>{}", cropFileDto);
        log.info("@crop/upload===========================>{}", cropFileDto.getFile());
        log.info("@crop/upload===========================>{}", cropFileDto.getUserId());
        log.info("@crop/upload===========================>{}", cropFileDto.getFilename());
        String destFilename = commonUtil.storeFile(cropFileDto.getFile(), fileUploadPath, cropFileDto.getFilename());
        return ResponseDto.of(destFilename);
    }


}
