package com.ssafy.enjoytrip.domain.hotplace.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.domain.hotplace.dto.HotPlaceDto.EditRequest;
import com.ssafy.enjoytrip.domain.hotplace.dto.HotPlaceDto.HotPlaceDetail;
import com.ssafy.enjoytrip.domain.hotplace.dto.HotPlaceDto.HotPlaceListView;
import com.ssafy.enjoytrip.domain.hotplace.service.HotPlaceService;
import com.ssafy.enjoytrip.domain.user.dto.UserDto.AuthInfo;
import com.ssafy.enjoytrip.global.validation.TokenVallidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/hotplaces")
@RequiredArgsConstructor
public class HotPlaceController {

	private final HotPlaceService hotPlaceService;
	
	@GetMapping("")
	public ResponseEntity<HotPlaceListView> getHotPlaces(@PageableDefault(sort = "createdAt", direction = Direction.ASC) Pageable pageable) {
		return new ResponseEntity<>(hotPlaceService.getHotPlaces(pageable), HttpStatus.OK);
	}

	@GetMapping("/{hotPlaceId}") 
	public ResponseEntity<HotPlaceDetail> getHotPlaceDetail(@PathVariable Integer hotPlaceId) {
		return new ResponseEntity<>(hotPlaceService.getHotPlaceDetail(hotPlaceId),HttpStatus.OK);
	}
	
	@DeleteMapping("/{hotPlaceId}")
	public ResponseEntity<Void> deleteHotPlace(@PathVariable Integer hotPlaceId) {
		hotPlaceService.deleteHotPlace(hotPlaceId);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("")
	public ResponseEntity<Void> saveHotPlace(
			@TokenVallidator AuthInfo authInfo, 
			@RequestPart EditRequest dto,
			@RequestPart(value="image") List<MultipartFile> images) {
		
		// TODO Save Image and get Image Path 
		hotPlaceService.insertHotPlace(dto, authInfo.getUserId());
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("")
	public ResponseEntity<Void> updateHotPlace(@RequestPart EditRequest dto) {
		hotPlaceService.updateHotPlace(dto);
		return ResponseEntity.ok().build();
	}
	
}
