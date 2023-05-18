package com.ssafy.enjoytrip.domain.comment.repository;

import java.util.List;

import com.ssafy.enjoytrip.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.domain.comment.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	List<Comment> findByPost(Post post);
}
