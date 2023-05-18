package com.ssafy.enjoytrip.domain.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.domain.post.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
	Page<Post> findByTitleContains(Pageable pageable, String title);
	Page<Post> findByContentContains(Pageable pageable, String content);
}
