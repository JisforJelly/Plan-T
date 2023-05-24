package com.ssafy.enjoytrip.domain.trip.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
		private String startDate;
		private List<EditTimeLineItem> timeLines;
	}
	
	@Data
	public static class EditTimeLineItem {
		private Integer tripPlanTimeLineId;
		private Integer tripPlanId;
		private Integer order;
		private Integer day;
		private String placeName;
		private Double latitude;
		private Double longitude;
		private String location;
		private String content;
		private String imgPath;
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
					.order(timeLine.getOrders())
					.placeName(timeLine.getPlaceName())
					.coordinate(Coordinate.from(timeLine))
					.location(timeLine.getLocation())
					.content(timeLine.getContent())
					.imgPath(timeLine.getImgPath())
					.startDate(timeLine.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
					.endDate(timeLine.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
					.build();
		}
	}
	
	@Data
	@Builder
	private static class TripPlanListItem {
		private Integer tripPlanId;
		private String title;
		private String imgPath;
		private String startDate;

		public static TripPlanListItem from(TripPlan tripPlan) {
			List<TripPlanTimeLine> timeLines = tripPlan.getTripPlanTimeLine();
			String imgPath = "";
			
			for(TripPlanTimeLine timeLine: timeLines) {
				if(timeLine.getImgPath() != null && timeLine.getImgPath().length() != 0) {
					imgPath = timeLine.getImgPath();
					break;
				}
			}
			
			LocalDate localDate = tripPlan.getStartDate();
			StringBuilder sb = new StringBuilder();
			sb.append(localDate.getYear()+"년 ");
			sb.append(localDate.getMonthValue()+"월  ");
			sb.append(localDate.getDayOfMonth()+"일 출발");
			return TripPlanListItem.builder()
					.tripPlanId(tripPlan.getTripPlanId())
					.imgPath(imgPath)
					.startDate(sb.toString())
					.title(tripPlan.getTitle())
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
					.order(timeLine.getOrders())
					.latitude(timeLine.getLatitude())
					.longitude(timeLine.getLongitude())
					.build();
		}
	}
}
