package com.ssafy.enjoytrip.domain.user.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.domain.user.dto.UserDto.AuthInfo;
import com.ssafy.enjoytrip.domain.user.service.UserService;
import com.ssafy.enjoytrip.global.util.FileUploadUtil;
import com.ssafy.enjoytrip.global.validation.TokenVallidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	private final FileUploadUtil fileUploadUtil;
	
	@PostMapping("")
	public ResponseEntity<String> updateUserInfo(
			@TokenVallidator AuthInfo authInfo,
			@RequestParam String name,
			@RequestPart(value="image", required=false) MultipartFile image) throws IOException {
		
		String imgPath = null;
		if(image != null) {
			imgPath = fileUploadUtil.saveImage(image);
		}
		userService.updateUserInfo(authInfo.getUserId(), imgPath, name);
		return new ResponseEntity<>(imgPath, HttpStatus.OK);
	}
}
