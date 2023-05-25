package com.ssafy.enjoytrip.domain.trip.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.domain.trip.entity.TripPlanGroup;

@Repository
public interface TripPlanGroupRepository extends JpaRepository<TripPlanGroup, Integer>{
	TripPlanGroup findByTripPlanTripPlanIdAndUserUserId(Integer tripPlanId, Integer userId);
	List<TripPlanGroup> findByTripPlanTripPlanId(Integer tripPlanId);
	Page<TripPlanGroup> findByUserUserId(Pageable pageable, Integer userId);
}
