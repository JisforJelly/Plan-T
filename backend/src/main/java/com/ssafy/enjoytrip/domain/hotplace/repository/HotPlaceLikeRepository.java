package com.ssafy.enjoytrip.domain.hotplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.domain.hotplace.entity.HotPlaceLike;

import java.util.List;

@Repository
public interface HotPlaceLikeRepository extends JpaRepository<HotPlaceLike, Integer> {
    HotPlaceLike findByHotplaceHotPlaceIdAndUserUserId(Integer hotplaceId, Integer userId);
    List<HotPlaceLike> findByUserUserId(Integer userId);
}
