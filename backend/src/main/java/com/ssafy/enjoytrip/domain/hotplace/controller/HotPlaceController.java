package com.ssafy.enjoytrip.domain.hotplace.controller;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.domain.hotplace.dto.HotPlaceDto;
import com.ssafy.enjoytrip.global.util.FileUploadUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
	private final FileUploadUtil fileUploadUtil;
	
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
	
	@PostMapping(path="", consumes = {"multipart/form-data"})
	public ResponseEntity<Void> saveHotPlace(
			@TokenVallidator AuthInfo authInfo,
			@RequestParam String dto,
			@RequestPart(value="image") List<MultipartFile> images) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		EditRequest editDto = objectMapper.readValue(dto, EditRequest.class);

		for(MultipartFile img: images) {
			String path = fileUploadUtil.saveImage(img);
			editDto.getImgPaths().add(path);
		}
		hotPlaceService.insertHotPlace(editDto, authInfo.getUserId());
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("")
	public ResponseEntity<Void> updateHotPlace(@RequestPart EditRequest dto) {
		hotPlaceService.updateHotPlace(dto);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{hotplaceId}/likes")
	public ResponseEntity<Void> toggleHotPlaceLike(
			@TokenVallidator AuthInfo authInfo,
			@PathVariable Integer hotplaceId) {
		hotPlaceService.toggleHotPlaceLike(hotplaceId, authInfo.getUserId());
		return ResponseEntity.ok().build();
	}

	@GetMapping("/likes")
	public ResponseEntity<HotPlaceDto.UserLikeHotPlace> getHotPlaceLike(
			@TokenVallidator AuthInfo authInfo) {
		return new ResponseEntity<>(hotPlaceService.getUserLikeHotPlaceId(authInfo.getUserId()), HttpStatus.OK);
	}
}
