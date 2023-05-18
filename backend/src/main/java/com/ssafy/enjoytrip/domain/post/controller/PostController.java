package com.ssafy.enjoytrip.domain.post.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.domain.post.dto.PostDto;
import com.ssafy.enjoytrip.domain.post.dto.PostDto.PostDetail;
import com.ssafy.enjoytrip.domain.post.dto.PostDto.PostListView;
import com.ssafy.enjoytrip.domain.post.service.PostService;
import com.ssafy.enjoytrip.domain.user.dto.UserDto.AuthInfo;
import com.ssafy.enjoytrip.global.validation.TokenVallidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;
	
	
	@GetMapping("")
	public ResponseEntity<PostListView> getPosts(
			@PageableDefault(sort = "createdAt", direction = Direction.ASC) Pageable pageable) {
		return new ResponseEntity<PostListView>(postService.getPosts(pageable), HttpStatus.OK);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<PostDetail> getPosts(@PathVariable Integer postId) {
		return new ResponseEntity<PostDetail>(postService.getPost(postId), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Void> savePost(@RequestBody PostDto.PostInsertRequest dto) {
		postService.savePost(1, dto);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/title/{title}")
	public ResponseEntity<PostListView> getPostsByTitle(@PageableDefault(sort = "createdAt", direction = Direction.ASC) Pageable pageable, @PathVariable String title) {
		return new ResponseEntity<PostListView>(postService.getPostsByTitle(pageable, title), HttpStatus.OK);
	}
	
	@GetMapping("/content/{content}")
	public ResponseEntity<PostListView> getPostsByContent(@PageableDefault(sort = "createdAt", direction = Direction.ASC) Pageable pageable, @PathVariable String content) {
		return new ResponseEntity<PostListView>(postService.getPostsByContents(pageable, content), HttpStatus.OK);
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<Void> deletePost(@TokenVallidator AuthInfo authInfo, @PathVariable Integer postId) {
		postService.deletePost(authInfo.getUserId(), postId);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("")
	public ResponseEntity<Void> modifyPost(@TokenVallidator AuthInfo authInfo, @RequestBody PostDto.UpdateRequest dto) {
		postService.modifyPost(authInfo.getUserId(), dto);
		return ResponseEntity.ok().build();
	}
}
