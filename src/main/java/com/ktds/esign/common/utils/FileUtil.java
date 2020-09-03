/*
 * IPCCP (IPC Container Platform) version 1.0
 * Copyright ⓒ 2018 kt corp. All rights reserved.
 * This is a proprietary software of kt corp, and you may not use this file except in
 * compliance with license agreement with kt corp. Any redistribution or use of this
 * software, with or without modification shall be strictly prohibited without prior written
 * approval of kt corp, and the copyright notice above does not evidence any actual or
 * intended publication of such software.
 */
package com.ktds.esign.common.utils;

import com.ktds.esign.common.exception.StorageException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Component
public class FileUtil {

    /**
     *  단건 파일 저장
     *
     * @param file
     * @param fileUploadPath
     * @param originalFilename
     */
    @SneakyThrows
    public String storeFile(MultipartFile file, String fileUploadPath, String originalFilename) {

        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file ");
        }

        String extension = FilenameUtils.getExtension(originalFilename);
        String destFilename = RandomStringUtils.randomAlphabetic(10)
                .concat(String.valueOf(System.currentTimeMillis()))
                .concat(".").concat(extension);

        File dir = new File(fileUploadPath);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        Files.copy(file.getInputStream(), Paths.get(fileUploadPath + destFilename), StandardCopyOption.REPLACE_EXISTING);
        return fileUploadPath + destFilename;
    }

    /**
     *  단건 파일 저장
     *
     * @param file
     * @param fileUploadPath
     * @param originalFilename
     */
    @SneakyThrows
    public String storeVideoFile(MultipartFile file, String fileUploadPath, String originalFilename) {

        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file ");
        }

        String extension = FilenameUtils.getExtension(originalFilename);
        String destFilename = RandomStringUtils.randomAlphabetic(10)
                .concat(String.valueOf(System.currentTimeMillis()))
                .concat(".").concat(extension);

        File dir = new File(fileUploadPath);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        Files.copy(file.getInputStream(), Paths.get(fileUploadPath + destFilename), StandardCopyOption.REPLACE_EXISTING);
        return destFilename;
    }

    /**
     * 단건 파일 삭제
     *
     * @param saveLocation
     * @param deleteFilename
     */
    @SneakyThrows
    public void deleteFile(String saveLocation, String deleteFilename) {
        Files.deleteIfExists(Paths.get(saveLocation + deleteFilename));
    }

}
