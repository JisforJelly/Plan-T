package com.ssafy.enjoytrip.domain.user.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.domain.user.dto.UserDto;
import com.ssafy.enjoytrip.domain.user.service.UserService;
import com.ssafy.enjoytrip.global.util.TokenUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	
	private final UserService userService;
	private final TokenUtil tokenUtil;
	
	@PostMapping("/sign-up")
	public ResponseEntity<Void> signUp(@RequestBody UserDto.SignUpRequest dto) {
		userService.signUp(dto);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/sign-in")
	public ResponseEntity<Void> signIn(@RequestBody UserDto.SignInRequest dto) {
		UserDto.AuthInfo authDto = userService.signIn(dto);
		String accessToken = tokenUtil.createAccessToken(authDto);
		return ResponseEntity.ok()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
				.build();
	}
}
