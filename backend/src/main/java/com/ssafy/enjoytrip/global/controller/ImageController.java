package com.ssafy.enjoytrip.global.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@CrossOrigin("*")
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    @Value("${file.imageDir}")
    public String impFolder;

    @GetMapping(value = "/{path}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> userSearch(@PathVariable("path") String path) throws IOException {
        InputStream imageStream = new FileInputStream(impFolder + File.separator + path);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
    }
}