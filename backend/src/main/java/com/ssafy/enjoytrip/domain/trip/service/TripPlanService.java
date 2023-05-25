package com.ssafy.enjoytrip.domain.trip.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.domain.trip.dto.TripDto.EditTimeLineItem;
import com.ssafy.enjoytrip.domain.trip.dto.TripDto.EditTripPlan;
import com.ssafy.enjoytrip.domain.trip.dto.TripDto.TripPlanDetail;
import com.ssafy.enjoytrip.domain.trip.dto.TripDto.TripPlanList;
import com.ssafy.enjoytrip.domain.trip.entity.TripPlan;
import com.ssafy.enjoytrip.domain.trip.entity.TripPlanGroup;
import com.ssafy.enjoytrip.domain.trip.entity.TripPlanTimeLine;
import com.ssafy.enjoytrip.domain.trip.repository.TripPlanGroupRepository;
import com.ssafy.enjoytrip.domain.trip.repository.TripPlanRepository;
import com.ssafy.enjoytrip.domain.trip.repository.TripPlanTimeLineRepository;
import com.ssafy.enjoytrip.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripPlanService {
	
	private final TripPlanRepository tripPlanRepository;
	private final TripPlanTimeLineRepository tripPlanTimeLineRepository;
	private final TripPlanGroupRepository tripPlanGroupRepository;
	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	public TripPlanList getTripPlans(Pageable pageable) {
		return TripPlanList.ofPage(tripPlanRepository.findAll(pageable));
	}
	
	@Transactional(readOnly = true)
	public TripPlanList getUserPlans(Pageable pageable, Integer userId) {
		List<TripPlanGroup> contents = tripPlanGroupRepository.findByUserUserId(pageable, userId).getContent();
		List<TripPlan> tripPlans = contents
				.stream()
				.map(TripPlanGroup::getTripPlan)
				.collect(Collectors.toList());
		
		return TripPlanList.ofList(tripPlans);
	}

	@Transactional
	public void insertTripPlan(EditTripPlan dto, Integer userId) {
		LocalDate startDate = LocalDate.parse(dto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		TripPlan tripPlan = tripPlanRepository.findById(dto.getTripPlanId()).orElse(null);
		if(tripPlan != null) {
			tripPlan.updateTitle(dto.getTitle());
			tripPlan.updateStartDate(LocalDate.parse(dto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			tripPlanTimeLineRepository.deleteAllByTripPlanTripPlanId(dto.getTripPlanId());
			tripPlanRepository.save(tripPlan);
		} else {
			tripPlan = TripPlan.builder()
					.title(dto.getTitle())
					.startDate(startDate)
					.user(userRepository.findById(userId).orElseThrow(IllegalArgumentException::new))
					.build();
			
			tripPlanRepository.save(tripPlan);
			tripPlanGroupRepository.save(TripPlanGroup.builder()
					.user(userRepository.findById(userId).orElseThrow(IllegalArgumentException::new))
					.tripPlan(tripPlan)
					.build());
		}
		
		List<TripPlanTimeLine> tripPlanTimeLines = makeTripTimeLine(dto, startDate, tripPlan);
		tripPlanTimeLineRepository.saveAll(tripPlanTimeLines);
	}

	private List<TripPlanTimeLine> makeTripTimeLine(EditTripPlan dto, LocalDate startDate, TripPlan tripPlan) {
		List<TripPlanTimeLine> tripPlanTimeLine = new LinkedList<>();
		List<EditTimeLineItem> timeLineDtos = dto.getTimeLines();
		for(EditTimeLineItem plan: timeLineDtos) {
			tripPlanTimeLine.add(TripPlanTimeLine.builder()
					.orders(plan.getOrder())
					.tripPlan(tripPlan)
					.placeName(plan.getPlaceName())
					.latitude(plan.getLatitude())
					.longitude(plan.getLongitude())
					.location(plan.getLocation())
					.content(plan.getContent())
					.startDate(startDate)
					.endDate(startDate = startDate.plusDays(plan.getDay()))
					.imgPath(plan.getImgPath()) 
					.build());
		}
		return tripPlanTimeLine;
	}

	@Transactional(readOnly = true)
	public TripPlanDetail getTripPlan(Integer tripPlanId) {
		return TripPlanDetail.from(tripPlanRepository.findById(tripPlanId).orElseThrow(IllegalArgumentException::new));
	}
	
	public void inviteTripPlan(Integer planId, Integer userId) {
		TripPlanGroup group = tripPlanGroupRepository.findByTripPlanTripPlanIdAndUserUserId(planId, userId);
		if(group == null) {
			tripPlanGroupRepository.save(TripPlanGroup.builder()
											.user(userRepository.findById(userId).orElseThrow(IllegalArgumentException::new))
											.tripPlan(tripPlanRepository.findById(planId).orElseThrow(IllegalArgumentException::new))
											.build()
										 );
		}
	}
	
	public boolean checkAuth(Integer tripPlanId, Integer userId) {
		return (tripPlanGroupRepository.findByTripPlanTripPlanIdAndUserUserId(tripPlanId, userId) != null);
	}

	@Transactional
	public void deleteTripPlan(Integer planId) {
		tripPlanRepository.deleteById(planId);
	}
}
