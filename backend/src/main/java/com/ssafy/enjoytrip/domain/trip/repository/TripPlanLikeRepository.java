package com.ssafy.enjoytrip.domain.trip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.domain.trip.entity.TripPlanLike;

@Repository
public interface TripPlanLikeRepository extends JpaRepository<TripPlanLike, Integer>{

}
