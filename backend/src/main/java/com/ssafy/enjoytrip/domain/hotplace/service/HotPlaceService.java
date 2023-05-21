package com.ssafy.enjoytrip.domain.hotplace.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.domain.hotplace.dto.HotPlaceDto.EditRequest;
import com.ssafy.enjoytrip.domain.hotplace.dto.HotPlaceDto.HotPlaceDetail;
import com.ssafy.enjoytrip.domain.hotplace.dto.HotPlaceDto.HotPlaceListView;
import com.ssafy.enjoytrip.domain.hotplace.entity.HotPlace;
import com.ssafy.enjoytrip.domain.hotplace.entity.HotPlaceImage;
import com.ssafy.enjoytrip.domain.hotplace.repository.HotPlaceRepository;
import com.ssafy.enjoytrip.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotPlaceService {
	
	private final HotPlaceRepository hotPlaceRepository;
	private final UserRepository userRepository;
	
	public void insertHotPlace(EditRequest dto, Integer userId) {
		HotPlace hotPlace = HotPlace
				.builder()
				.user(userRepository.findById(userId).orElseThrow(IllegalArgumentException::new))
				.title(dto.getTitle())
				.content(dto.getContent())
				.location(dto.getLocation())
				.latitude(dto.getLatitude())
				.longitude(dto.getLongitude())
				.hashTag(dto.getHashTag())
				.contentType(dto.getContentType())
				.build();

		List<HotPlaceImage> paths = dto.getImgPaths().stream().map((path)->
				HotPlaceImage.builder().imagePath(path).hotplace(hotPlace).build()).collect(Collectors.toList());

		hotPlace.setImages(paths);
		hotPlaceRepository.save(hotPlace);
	}
	
	public void updateHotPlace(EditRequest dto) {
		HotPlace hotPlace = hotPlaceRepository
				.findById(dto.getHotPlaceId())
				.orElseThrow(IllegalArgumentException::new);
		
		hotPlace.updateContent(dto.getContent());
		hotPlace.updateHashTag(dto.getHashTag());
		hotPlace.updateLocationInfo(dto.getLocation(), dto.getLatitude(), dto.getLongitude());
		hotPlace.updateHashTag(dto.getHashTag());
		hotPlace.updateContentType(dto.getContentType());
		hotPlaceRepository.save(hotPlace);
	}
	
	public void deleteHotPlace(Integer hotPlaceId) {
		hotPlaceRepository.deleteById(hotPlaceId);
	}
	
	public HotPlaceListView getHotPlaces(Pageable pageable) {
		return HotPlaceListView.ofPage(hotPlaceRepository.findAll(pageable));
	}
	
	public HotPlaceDetail getHotPlaceDetail(int hotPlaceId) {
		return HotPlaceDetail.from(hotPlaceRepository.findById(hotPlaceId).orElseThrow(IllegalArgumentException::new));
	}
}
