package com.ssafy.enjoytrip.global.controller;

import com.ssafy.enjoytrip.global.code.meta.MetaData;
import com.ssafy.enjoytrip.global.code.meta.MetaDataFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/meta")
@RequiredArgsConstructor
public class MetaDataController {

    private final MetaDataFactory factory;

    @GetMapping("/{type}")
    public ResponseEntity<List<MetaData>> getMetaData(@PathVariable String type) {
        return new ResponseEntity<>(factory.get(type), HttpStatus.OK);
    }
}
