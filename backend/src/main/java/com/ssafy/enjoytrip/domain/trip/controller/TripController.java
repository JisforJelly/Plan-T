package com.ssafy.enjoytrip.domain.trip.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.domain.user.dto.UserDto.AuthInfo;
import com.ssafy.enjoytrip.global.validation.TokenVallidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

	// TODO : 여행 계획 조회 (페이지 네이션 필요)
	@GetMapping("")
	public ResponseEntity<?> getTripPlans() {
		return ResponseEntity.ok().build();
	}
	
	// TODO : 여행 계획 상세 조회
	@GetMapping("/{tripPlanId}")
	public ResponseEntity<?> getTripPlan(@PathVariable Integer tripPlanId) {
		return ResponseEntity.ok().build();
	}
	
	// TODO : 여행 계획 등록
	@PostMapping("")
	public ResponseEntity<Void> insertTripPlan(
			@TokenVallidator AuthInfo authinfo) {
		return ResponseEntity.ok().build();
	}
	
	// TODO : 여행 계획 업데이트
	@PostMapping("/{tripPlanId}")
	public ResponseEntity<Void> updateTripPlan(@PathVariable Integer tripPlanId) {
		return ResponseEntity.ok().build();
	}
	
	// TODO : 여행 계획 좋아요(북마크)
	@PostMapping("/like/{tripPlanId}")
	public ResponseEntity<Void> togglerTripPlanLike(
			@TokenVallidator AuthInfo authinfo,
			@PathVariable Integer tripPlanId) {
		
		return ResponseEntity.ok().build();
	}
	
	// TODO : 여행 계획 친구 초대(수정 권한 부여)
	@PostMapping("/invite/{tripPlanId}")
	public ResponseEntity<Void> inviteTripPlan(
			@TokenVallidator AuthInfo authinfo,
			@PathVariable Integer tripPlanId) {
		
		return ResponseEntity.ok().build();
	}
	
	// TODO : 여행 계획 삭제
	@DeleteMapping("/{tripPlanId}")
	public ResponseEntity<Void> deleteTripPlan(@PathVariable Integer tripPlanId) {
		return ResponseEntity.ok().build();
	}
}
