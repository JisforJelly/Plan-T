package com.ssafy.enjoytrip.domain.hotplace.dto;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.ssafy.enjoytrip.domain.hotplace.entity.HotPlace;
import com.ssafy.enjoytrip.domain.hotplace.entity.HotPlaceImage;

import lombok.Builder;
import lombok.Data;

public class HotPlaceDto {
	
	private static int LIST_LENGTH_LIMIT = 50;
	
	@Data
	@Builder
	public static class HotPlaceDetail {
		private Integer hotPlaceId;
		private String userName;
		private String title;
		private String content;
		private String location;
		private Double latitude;
		private Double longitude;
		private String hashTag;
		private String contentType;
		private List<String> images;
		
		public static HotPlaceDetail from(HotPlace hotPlace) {
			List<String> imgPaths = hotPlace.getImages()
					.stream()
					.map(HotPlaceImage::getImagePath).collect(Collectors.toList());
			
			return HotPlaceDetail.builder()
					.hotPlaceId(hotPlace.getHotPlaceId())
					.userName(hotPlace.getUser().getName())
					.title(hotPlace.getTitle())
					.content(hotPlace.getContent())
					.location(hotPlace.getLocation())
					.latitude(hotPlace.getLatitude())
					.longitude(hotPlace.getLongitude())
					.hashTag(hotPlace.getHashTag())
					.contentType(hotPlace.getContentType())
					.images(imgPaths)
					.build();
		}
	}
	
	@Data
	public static class HotPlaceListView {
		private List<HotPlaceListItem> hotPlaces;
    	private int currentPage;
    	private int totalPages;
    	private boolean isLastPage;
    	
		public HotPlaceListView(List<HotPlaceListItem> hotPlaces, int currentPage, int totalPages, boolean isLastPage) {
			this.hotPlaces = hotPlaces;
			this.currentPage = currentPage;
			this.totalPages = totalPages;
			this.isLastPage = isLastPage;
		}
		
		public static HotPlaceListView ofPage(Page<HotPlace> hotPlaces) {
			List<HotPlaceListItem> items = hotPlaces.getContent()
					.stream()
					.map(HotPlaceListItem::from)
					.collect(Collectors.toList());
			
			return new HotPlaceListView(items, hotPlaces.getNumber(), hotPlaces.getTotalPages(), hotPlaces.isLast());
		}
	} 
	
	@Data
	@Builder
	public static class HotPlaceListItem {
		private Integer hotPlaceId;
		private List<String> imgPaths;
		private String title;
		private String shortContents;
		private String hashTag;
		
		public static HotPlaceListItem from(HotPlace hotPlace) {
			List<String> imgPaths = hotPlace.getImages()
					.stream()
					.map(img->img.getImagePath()).collect(Collectors.toList());
			
			String content = hotPlace.getContent();
			
			return HotPlaceListItem
					.builder()
					.hotPlaceId(hotPlace.getHotPlaceId())
					.imgPaths(imgPaths)
					.title(hotPlace.getTitle())
					.shortContents(content.substring(0, Math.min(content.length(), LIST_LENGTH_LIMIT)))
					.hashTag(hotPlace.getHashTag())
					.build();
		}
	}
	
	@Data
	public static class EditRequest {
		private Integer hotPlaceId;
		private String title;
		private String content;
		private String location;
		private Double latitude;
		private Double longitude;
		private String hashTag;
		private String contentType;
		private List<String> imgPaths = new LinkedList<>();
	}
}
