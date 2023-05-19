package com.ssafy.enjoytrip.domain.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.domain.comment.dto.CommentDto;
import com.ssafy.enjoytrip.domain.comment.dto.CommentDto.CommentListView;
import com.ssafy.enjoytrip.domain.comment.entity.Comment;
import com.ssafy.enjoytrip.domain.comment.repository.CommentRepository;
import com.ssafy.enjoytrip.domain.post.repository.PostRepository;
import com.ssafy.enjoytrip.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	
	private final UserRepository userRepository;
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;

	public CommentListView findByPostId(Integer postId) {
		List<Comment> comments = commentRepository
				.findByPost(postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException()));
		
		return CommentListView.ofList(comments);
	}

	public void insertComment(CommentDto.EditRequest dto, Integer userId) {
		Comment comment = Comment.builder()
				.content(dto.getContent())
				.post(postRepository.findById(dto.getPostId()).orElseThrow(()->new IllegalArgumentException()))
				.user(userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException()))
				.build();
		
		commentRepository.save(comment);
	}
	
	public void deleteComment(Integer userId, Integer commentId) {
		commentRepository.deleteById(commentId);
	}
	
	public void updateComment(Integer userId, Integer commentId, CommentDto.EditRequest dto) {
		Comment comment = commentRepository.findById(commentId).orElseThrow(()->new IllegalArgumentException());
		comment.updateContent(dto.getContent());
		commentRepository.save(comment);
	}
}
