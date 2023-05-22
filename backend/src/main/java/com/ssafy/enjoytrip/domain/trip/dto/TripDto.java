package com.ssafy.enjoytrip.domain.trip.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.ssafy.enjoytrip.domain.trip.entity.TripPlan;
import com.ssafy.enjoytrip.domain.trip.entity.TripPlanTimeLine;

import lombok.Builder;
import lombok.Data;

public class TripDto {
	
	@Data
	public static class EditTripPlan {
		private String title;
		private List<EditTimeLineItem> timeLines;
	}
	
	@Data
	public static class EditTimeLineItem {
		private Integer tripPlanTimeLineId;
		private Integer tripPlanId;
		private Integer order;
		private String placeName;
		private Double latitude;
		private Double longitude;
		private String location;
		private String content;
		private String imgPath;
		private String startDate;
		private String endDate;
	}
	
	@Data
	public static class TripPlanList {
		private List<TripPlanListItem> tripPlans;
    	private int currentPage;
    	private int totalPages;
    	private boolean isLastPage;
    	
    	public TripPlanList(
    			List<TripPlanListItem> tripPlans,
    			int currentPage,
    			int totalPages,
    			boolean isLastPage) {
    		this.tripPlans = tripPlans;
    		this.currentPage = currentPage;
    		this.totalPages = totalPages;
    		this.isLastPage = isLastPage;
    	}
    	
    	public static TripPlanList ofPage(Page<TripPlan> tripPlans) {
    		List<TripPlanListItem> items = tripPlans.getContent()
    				.stream().map(TripPlanListItem::from)
    				.collect(Collectors.toList());
    		
    		return new TripPlanList(
    				items, 
    				tripPlans.getNumber(), 
    				tripPlans.getTotalPages(), 
    				tripPlans.isLast());
    	}
	}
	
	@Data
	@Builder
	public static class TripPlanDetail {
		private Integer tripPlanId;
		private Integer userId;
		private String userName;
		private String title;
		private List<TripPlanDetailItem> destinations;
		
		public static TripPlanDetail from(TripPlan tripPlan) {
			List<TripPlanDetailItem> items = tripPlan.getTripPlanTimeLine()
					.stream()
					.map(TripPlanDetailItem::from)
					.collect(Collectors.toList());
			
			return TripPlanDetail.builder()
					.tripPlanId(tripPlan.getTripPlanId())
					.userId(tripPlan.getUser().getUserId())
					.userName(tripPlan.getUser().getName())
					.title(tripPlan.getTitle())
					.destinations(items)
					.build();
		}
	}
	
	@Data
	@Builder
	public static class TripPlanDetailItem {
		private Integer tripPlanTimeLineId;
		private Integer order;
		private String placeName;
		private Coordinate coordinate;
		private String location;
		private String content;
		private String imgPath;
		private String startDate;
		private String endDate;
		
		public static TripPlanDetailItem from(TripPlanTimeLine timeLine) {
			return TripPlanDetailItem.builder()
					.tripPlanTimeLineId(timeLine.getTripPlanTimeLineId())
					.order(timeLine.getOrder())
					.placeName(timeLine.getPlaceName())
					.coordinate(Coordinate.from(timeLine))
					.location(timeLine.getLocation())
					.content(timeLine.getContent())
					.imgPath(timeLine.getImgPath())
					.startDate("TODO FORMATING")
					.endDate("")
					.build();
		}
	}
	
	@Data
	@Builder
	private static class TripPlanListItem {
		private Integer tripPlanId;
		private String title;
		private List<Coordinate> paths;
		
		public static TripPlanListItem from(TripPlan tripPlan) {
			List<Coordinate> paths = tripPlan.getTripPlanTimeLine()
					.stream().map(Coordinate::from)
					.collect(Collectors.toList());
			
			return TripPlanListItem.builder()
					.tripPlanId(tripPlan.getTripPlanId())
					.title(tripPlan.getTitle())
					.paths(paths)
					.build();
		}
	}
	
	@Data
	@Builder
	private static class Coordinate {
		private Integer order;
		private Double latitude;
		private Double longitude;
		
		public static Coordinate from(TripPlanTimeLine timeLine) {
			return Coordinate.builder()
					.order(timeLine.getOrder())
					.latitude(timeLine.getLatitude())
					.longitude(timeLine.getLongitude())
					.build();
		}
	}
}
