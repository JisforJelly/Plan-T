package com.ssafy.enjoytrip.domain.post.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.domain.post.dto.PostDto;
import com.ssafy.enjoytrip.domain.post.entity.Post;
import com.ssafy.enjoytrip.domain.post.repository.PostRepository;
import com.ssafy.enjoytrip.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	
	private final UserRepository userRepository;
	private final PostRepository postRepository;
	
	@Transactional
	public void savePost(Integer userId, PostDto.PostInsertRequest dto) {
		postRepository.save(Post.builder()
				.title(dto.getTitle())
				.content(dto.getContent())
				.viewCnt(0)
				.user(userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException()))
				.build());
	}
	
	@Transactional
	public void deletePost(Integer userId, Integer postId) {
		Post post = findorThrowByPostId(postId);
		checkPostOwnerByUserId(post, userId);
		postRepository.deleteById(postId);
	}
	
	@Transactional
	public void modifyPost(Integer userId, PostDto.UpdateRequest dto) {
		Post post = findorThrowByPostId(dto.getPostId());
		checkPostOwnerByUserId(post, userId);
		post.updateTitle(dto.getTitle());
		post.updateContent(dto.getContent());
		System.out.println("테스트 테스트 " + dto.getTitle() + " " + dto.getContent());
	}

	@Transactional(readOnly=true)
	public PostDto.PostListView getPosts(Pageable pageable) {
		return PostDto.PostListView.ofPage(postRepository.findAll(pageable));
	}
	
	@Transactional(readOnly=true)
	public PostDto.PostListView getPostsByTitle(Pageable pageable, String title) {
		return PostDto.PostListView.ofPage(postRepository.findByTitleContains(pageable, title));
	}
	
	@Transactional(readOnly=true)
	public PostDto.PostListView getPostsByContents(Pageable pageable, String content) {
		return PostDto.PostListView.ofPage(postRepository.findByContentContains(pageable, content));
	}
	
	@Transactional
	public PostDto.PostDetail getPost(Integer postId) {
		Post post = findorThrowByPostId(postId);
		post.updateViewCnt(post.getViewCnt()+1);
		return PostDto.PostDetail.from(post);
	}
	 
	private Post findorThrowByPostId(Integer postId) {
		return postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException());
	}
	
	private boolean checkPostOwnerByUserId(Post post, Integer userId) {
		return post.getUser().getUserId() == userId;
	}
}
