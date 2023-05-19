package com.ssafy.enjoytrip.domain.hotplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.domain.hotplace.entity.HotPlace;

@Repository
public interface HotPlaceRepository extends JpaRepository<HotPlace, Integer> {

}
