package com.ssafy.enjoytrip.domain.trip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.domain.trip.entity.TripPlanGroup;

@Repository
public interface TripPlanGroupRepository extends JpaRepository<TripPlanGroup, Integer>{

}
