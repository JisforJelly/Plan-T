package com.ssafy.enjoytrip.domain.trip.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import com.ssafy.enjoytrip.domain.trip.repository.TripPlanTimeLineRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.domain.trip.dto.TripDto.EditTimeLineItem;
import com.ssafy.enjoytrip.domain.trip.dto.TripDto.EditTripPlan;
import com.ssafy.enjoytrip.domain.trip.dto.TripDto.TripPlanDetail;
import com.ssafy.enjoytrip.domain.trip.dto.TripDto.TripPlanList;
import com.ssafy.enjoytrip.domain.trip.entity.TripPlan;
import com.ssafy.enjoytrip.domain.trip.entity.TripPlanTimeLine;
import com.ssafy.enjoytrip.domain.trip.repository.TripPlanGroupRepository;
import com.ssafy.enjoytrip.domain.trip.repository.TripPlanLikeRepository;
import com.ssafy.enjoytrip.domain.trip.repository.TripPlanRepository;
import com.ssafy.enjoytrip.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TripPlanService {
	
	private final TripPlanRepository tripPlanRepository;
	private final TripPlanTimeLineRepository tripPlanTimeLineRepository;
	private final TripPlanGroupRepository tripPlanGroupRepository;
	private final TripPlanLikeRepository tripPlanLikeRepository;
	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	public TripPlanList getTripPlans(Pageable pageable) {
		return TripPlanList.ofPage(tripPlanRepository.findAll(pageable));
	}

	@Transactional
	public void insertTripPlan(EditTripPlan dto, Integer userId) {
		LocalDate startDate = LocalDate.parse(dto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		TripPlan tripPlan = tripPlanRepository.findById(dto.getTripPlanId()).orElse(null);
		if(tripPlan != null) {
			tripPlan.updateTitle(dto.getTitle());
			tripPlan.updateStartDate(LocalDate.parse(dto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			tripPlanTimeLineRepository.deleteAllByTripPlanTripPlanId(dto.getTripPlanId());
		} else {
			tripPlan = TripPlan.builder()
					.title(dto.getTitle())
					.startDate(startDate)
					.user(userRepository.findById(userId).orElseThrow(IllegalArgumentException::new))
					.build();
		}
		
		tripPlanRepository.save(tripPlan);
		
		System.out.println(tripPlan.getTripPlanId());
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
	
	public void toggleTripPlanLike() {}
	
	public void inviteTripPlan() {}

	@Transactional
	public void deleteTripPlan(Integer planId) {
		tripPlanRepository.deleteById(planId);
	}
}
