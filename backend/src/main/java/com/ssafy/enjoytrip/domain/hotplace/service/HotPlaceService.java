package com.ssafy.enjoytrip.domain.hotplace.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.domain.hotplace.dto.HotPlaceDto;
import com.ssafy.enjoytrip.domain.hotplace.dto.HotPlaceDto.EditRequest;
import com.ssafy.enjoytrip.domain.hotplace.dto.HotPlaceDto.HotPlaceDetail;
import com.ssafy.enjoytrip.domain.hotplace.dto.HotPlaceDto.HotPlaceListView;
import com.ssafy.enjoytrip.domain.hotplace.entity.HotPlace;
import com.ssafy.enjoytrip.domain.hotplace.entity.HotPlaceImage;
import com.ssafy.enjoytrip.domain.hotplace.entity.HotPlaceLike;
import com.ssafy.enjoytrip.domain.hotplace.repository.HotPlaceLikeRepository;
import com.ssafy.enjoytrip.domain.hotplace.repository.HotPlaceRepository;
import com.ssafy.enjoytrip.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotPlaceService {

	private final HotPlaceRepository hotPlaceRepository;
	private final HotPlaceLikeRepository hotPlaceLikeRepository;
	private final UserRepository userRepository;
	
	@Transactional
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
	
	@Transactional
	public void updateHotPlace(EditRequest dto) {
		HotPlace hotPlace = hotPlaceRepository
				.findById(dto.getHotPlaceId())
				.orElseThrow(IllegalArgumentException::new);
		
		hotPlace.updateTitle(dto.getTitle());
		hotPlace.updateContent(dto.getContent());
		hotPlace.updateHashTag(dto.getHashTag());
		hotPlace.updateLocationInfo(dto.getLocation(), dto.getLatitude(), dto.getLongitude());
		hotPlace.updateHashTag(dto.getHashTag());
		hotPlace.updateContentType(dto.getContentType());
		hotPlaceRepository.save(hotPlace);
	}
	
	@Transactional
	public void deleteHotPlace(Integer hotPlaceId) {
		hotPlaceRepository.deleteById(hotPlaceId);
	}
	
	@Transactional(readOnly=true)
	public HotPlaceListView getHotPlaces(Pageable pageable) {
		return HotPlaceListView.ofPage(hotPlaceRepository.findAll(pageable));
	}
	
	@Transactional(readOnly=true)
	public HotPlaceListView getUserLikeHotPlaces(Pageable pageable, Integer userId) {
		return HotPlaceListView.ofPage(hotPlaceRepository.findByLikesUserUserId(pageable, userId));
	}
	
	@Transactional(readOnly=true)
	public HotPlaceDetail getHotPlaceDetail(int hotPlaceId) {
		return HotPlaceDetail.from(hotPlaceRepository.findById(hotPlaceId).orElseThrow(IllegalArgumentException::new));
	}

	@Transactional
	public void toggleHotPlaceLike(int hotPlaceId, int userId) {
		HotPlaceLike hotPlaceLike = hotPlaceLikeRepository.findByHotplaceHotPlaceIdAndUserUserId(hotPlaceId, userId);
		if(hotPlaceLike == null) {
			insertHotPlaceLike(hotPlaceId, userId);
		} else {
			hotPlaceLikeRepository.delete(hotPlaceLike);
		}
	}
	
	@Transactional(readOnly=true)
	public boolean isLike(int hotPlaceId, int userId) {
		return hotPlaceLikeRepository.findByHotplaceHotPlaceIdAndUserUserId(hotPlaceId, userId) != null;
	}

	@Transactional(readOnly=true)
	public HotPlaceDto.UserLikeHotPlace getUserLikeHotPlaceId(int userId) {
		return new HotPlaceDto.UserLikeHotPlace(hotPlaceLikeRepository.findByUserUserId(userId));
	}

	private void insertHotPlaceLike(int hotPlaceId, int userId) {
		hotPlaceLikeRepository.save(HotPlaceLike.builder()
				.user(userRepository.findById(userId).orElseThrow(IllegalArgumentException::new))
				.hotplace(hotPlaceRepository.findById(hotPlaceId).orElseThrow(IllegalArgumentException::new))
				.build());
	}
}
