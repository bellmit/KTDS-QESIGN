package com.ktds.esign.client.example.controller;

import com.ktds.esign.client.example.payload.BoardReq.BoardDto;
import com.ktds.esign.client.example.payload.FileUploadDto;
import com.ktds.esign.client.example.service.EditorService;
import com.ktds.esign.common.utils.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("example/editor")
public class EditorController {

    private final CommonUtil commonUtil;
    private final EditorService editorService;

    @Value("${app.file.upload.path}")
    private String fileUploadPath;

    @GetMapping
    public ModelAndView editor(ModelAndView mav) {
        mav.setViewName("views/example/editor");
        return mav;
    }

    @PostMapping("save")
    public ResponseEntity<?> savePost(@RequestBody BoardDto boardDto) {
        return new ResponseEntity<>(editorService.savePost(boardDto), HttpStatus.CREATED);
    }

    @PostMapping(value = "/upload/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveImage(@RequestPart MultipartFile upload, HttpServletRequest request) { //CKEditor 에서 "upload" 로 보냄
        log.info("@editor/upload/image======================>{}", upload.getOriginalFilename());
        log.info("@editor/upload/fileUploadPath=============>{}", fileUploadPath);
        String originalFilename = upload.getOriginalFilename();
        String destFilename = commonUtil.storeFile(upload, fileUploadPath, originalFilename);
        String imgUrl = request.getScheme().concat("://").concat(request.getServerName()).concat(":")
                .concat(String.valueOf(request.getServerPort())).concat("/upload/").concat(destFilename);
        log.info("@editor/upload/imgUrl=====================>{}", imgUrl);
        FileUploadDto fileUploadDto = FileUploadDto.builder()
                .uploaded(1) // 0: fail, 1: success
                .filename(destFilename)
                .url(imgUrl)
                .build();
        return new ResponseEntity<>(fileUploadDto, HttpStatus.OK);
    }

}
