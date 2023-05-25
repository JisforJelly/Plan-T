package com.ssafy.enjoytrip.domain.trip.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.domain.trip.dto.TripDto.EditTripPlan;
import com.ssafy.enjoytrip.domain.trip.dto.TripDto.TripPlanDetail;
import com.ssafy.enjoytrip.domain.trip.dto.TripDto.TripPlanList;
import com.ssafy.enjoytrip.domain.trip.service.TripPlanService;
import com.ssafy.enjoytrip.domain.user.dto.UserDto.AuthInfo;
import com.ssafy.enjoytrip.global.validation.TokenVallidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {	
	
	private final TripPlanService tripPlanService;
	
	@GetMapping("")
	public ResponseEntity<TripPlanList> getTripPlans(@PageableDefault(sort = "createdAt", direction = Direction.ASC) Pageable pageable) {
		return new ResponseEntity<>(tripPlanService.getTripPlans(pageable), HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<TripPlanList> getUserTripPlans(
			@PageableDefault(sort = "createdAt", direction = Direction.ASC) Pageable pageable,
			@TokenVallidator AuthInfo authInfo) {
		
		return new ResponseEntity<>(tripPlanService.getUserPlans(pageable, authInfo.getUserId()), HttpStatus.OK);
	}
	
	
	@GetMapping("/{tripPlanId}")
	public ResponseEntity<TripPlanDetail> getTripPlan(@PathVariable Integer tripPlanId) {
		return new ResponseEntity<>(tripPlanService.getTripPlan(tripPlanId), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Void> insertTripPlan(
			@TokenVallidator AuthInfo authInfo,
			@RequestBody EditTripPlan dto) {
		
		tripPlanService.insertTripPlan(dto, authInfo.getUserId());
		return ResponseEntity.ok().build();
	}

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
	
	@PostMapping("/invite/{tripPlanId}")
	public ResponseEntity<Void> inviteTripPlan(
			@TokenVallidator AuthInfo authinfo,
			@PathVariable Integer tripPlanId,
			@RequestParam Integer userId) {
		
		tripPlanService.inviteTripPlan(tripPlanId, userId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/authority")
	public ResponseEntity<Boolean> hasEditAuth(
			@TokenVallidator AuthInfo authinfo,
			@RequestParam Integer tripPlanId) {
		
		return new ResponseEntity<>(tripPlanService.checkAuth(tripPlanId, authinfo.getUserId()), HttpStatus.OK);
	}

	@DeleteMapping("/{tripPlanId}")
	public ResponseEntity<Void> deleteTripPlan(@PathVariable Integer tripPlanId) {
		tripPlanService.deleteTripPlan(tripPlanId);
		return ResponseEntity.ok().build();
	}
}
