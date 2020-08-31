package com.ktds.esign.client.example.controller;

import com.ktds.esign.client.example.payload.VideoReq.VideoDto;
import com.ktds.esign.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("example/video")
public class VideoController {

    @Value("${app.file.location}")
    private String fileUploadPath;

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

}
