package com.ssafy.enjoytrip.domain.comment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.domain.comment.dto.CommentDto;
import com.ssafy.enjoytrip.domain.comment.dto.CommentDto.CommentListView;
import com.ssafy.enjoytrip.domain.comment.service.CommentService;
import com.ssafy.enjoytrip.domain.user.dto.UserDto.AuthInfo;
import com.ssafy.enjoytrip.global.validation.TokenVallidator;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
	
	private final CommentService commentService;
	@GetMapping("")
	public ResponseEntity<CommentListView> getComments(@RequestParam Integer postId) {
		return new ResponseEntity<>(commentService.findByPostId(postId), HttpStatus.OK);
	}

	
	@PostMapping("")
	public ResponseEntity<Void> insertComment(@TokenVallidator AuthInfo authInfo, @RequestBody CommentDto.EditRequest dto) {
		commentService.insertComment(dto, authInfo.getUserId());
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<Void> deleteComment(@TokenVallidator AuthInfo authInfo, @PathVariable Integer commentId) {
		commentService.deleteComment(authInfo.getUserId(), commentId);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{commentId}")
	public ResponseEntity<Void> updateComment(@TokenVallidator AuthInfo authInfo, @PathVariable Integer commentId, @RequestBody CommentDto.EditRequest dto) {
		commentService.updateComment(authInfo.getUserId(), commentId, dto);
		return ResponseEntity.ok().build();
	}
}
