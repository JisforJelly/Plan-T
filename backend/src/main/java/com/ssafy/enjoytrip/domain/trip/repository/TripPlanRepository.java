package com.ssafy.enjoytrip.domain.trip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.domain.trip.entity.TripPlan;

@Repository
public interface TripPlanRepository extends JpaRepository<TripPlan, Integer>{
}
