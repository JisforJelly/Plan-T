package com.ssafy.enjoytrip.domain.user.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.domain.user.controller.AuthEmailDto.FindLoginIdRequest;
import com.ssafy.enjoytrip.domain.user.dto.UserDto;
import com.ssafy.enjoytrip.domain.user.service.AuthMailService;
import com.ssafy.enjoytrip.domain.user.service.UserService;
import com.ssafy.enjoytrip.global.util.TokenUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthMailService mailService;
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
	
	@PostMapping("/find-id")
	public ResponseEntity<String> findLoginId(@RequestBody FindLoginIdRequest dto) {
		boolean isSuccess = mailService.sendLoginId(dto.getEmail());
		return new ResponseEntity<>(isSuccess ? "이메일을 확인해주세요." : "존재하지 않는 회원입니다.", HttpStatus.OK);
	}

	@PostMapping("/find-password")
	public ResponseEntity<String> findPassword(@RequestBody AuthEmailDto.FindPasswordRequest dto) {
		boolean isSuccess = mailService.sendFindPasswordUrl(dto.getLoginId(), dto.getEmail());
		return new ResponseEntity<>(isSuccess ? "이메일을 확인해주세요." : "존재하지 않는 회원입니다.", HttpStatus.OK);
	}
}
