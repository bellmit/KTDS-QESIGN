package com.ktds.esign.client.example.controller;

import com.ktds.esign.client.example.payload.VideoReq.VideoDto;
import com.ktds.esign.client.example.payload.VideoReq.VideoUploadDto;
import com.ktds.esign.common.response.ResponseDto;
import com.ktds.esign.common.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("example/video")
public class VideoController {

    @Value("${app.file.location}")
    private String fileUploadPath;

    private final FileUtil commonUtil;

    @GetMapping("view")
    public ModelAndView video(ModelAndView mav) {
        mav.setViewName("views/example/video");
        return mav;
    }

    @GetMapping("request/{filename}")
    public ResponseDto<?> getNewVideo(@PathVariable String filename, HttpServletRequest request) { //CKEditor 에서 "upload" 로 보냄
        log.info("@filename======================>{}", filename);
        // @todo search video file and file path by filename
        String video = filename + ".mp4";
        String videoUrl = request.getScheme().concat("://").concat(request.getServerName()).concat(":")
                .concat(String.valueOf(request.getServerPort())).concat(fileUploadPath).concat(video);
        log.info("@request videoUrl==============>{}", videoUrl);
        VideoDto videoDto = VideoDto.builder()
                .videoUrl(videoUrl)
                .videoPlaySec(15)
                .build();

        return ResponseDto.of(videoDto);
    }

    @GetMapping("view2")
    public ModelAndView video2(ModelAndView mav) {
        mav.setViewName("views/example/video2");
        return mav;
    }

    /**
     * 영상 저장
     * 
     * @param videoUploadDto
     * @return
     */
    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto<?> saveVideo(@ModelAttribute final VideoUploadDto videoUploadDto, HttpServletRequest request) {
        log.info("@videoUploadDto========================>{}", videoUploadDto);
        log.info("@videoUploadDto========================>{}", videoUploadDto.getFile());
        log.info("@videoUploadDto========================>{}", videoUploadDto.getFile().getSize());
        log.info("@videoUploadDto========================>{}", videoUploadDto.getFormName());
        log.info("@videoUploadDto========================>{}", videoUploadDto.getFormDesc());
        log.info("@videoUploadDto========================>{}", videoUploadDto.getFormType());

        String destFilename = commonUtil.storeVideoFile(videoUploadDto.getFile(), fileUploadPath, videoUploadDto.getFile().getOriginalFilename());
        String videoUrl = request.getScheme().concat("://").concat(request.getServerName()).concat(":")
                .concat(String.valueOf(request.getServerPort())).concat(fileUploadPath).concat(destFilename);
        log.info("@request videoUrl==============>{}", videoUrl);
        return ResponseDto.of(videoUrl);
    }

}
