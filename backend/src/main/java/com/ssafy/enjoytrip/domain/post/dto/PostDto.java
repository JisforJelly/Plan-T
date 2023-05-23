package com.ssafy.enjoytrip.domain.post.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.ssafy.enjoytrip.domain.post.entity.Post;

import lombok.Builder;
import lombok.Data;

public class PostDto {
	
    @Data
    public static class PostInsertRequest {
        private String title;   // 글 제목
        private String content; // 글 컨텐츠
    }

    @Data
    public static class PostListView {
    	private List<PostListItem> posts;
    	private int currentPage;
    	private int totalPages;
    	private boolean isLastPage;
    	
    	public PostListView(List<PostListItem> posts, int currentPage, int totalPages, boolean isLastPage) {
    		this.posts = posts;
    		this.currentPage = currentPage+1;
    		this.totalPages = totalPages;
    		this.isLastPage = isLastPage;
    	}
    	
    	public static PostListView ofPage(Page<Post> postPages) {
    		List<PostListItem> posts = postPages.getContent()
    				.stream()
    				.map(PostListItem::from)
    				.collect(Collectors.toList());
    		
    		return new PostListView(posts, postPages.getNumber(), postPages.getTotalPages(), postPages.isLast());
    	}
    }
    
    @Data
    @Builder
    public static class PostListItem {
        private String title;       // 글 제목
        private String name;    // 작성 유저명
        private LocalDateTime createdAt; // 글 작성 시간
        private int postId;         // 글 PK
        private int viewCnt;          // 조회 수
        
        public static PostListItem from(Post post) {
        	return PostListItem
        			.builder()
        			.title(post.getTitle())
        			.name(post.getUser().getName())
        			.createdAt(post.getCreatedAt())
        			.postId(post.getPostId())
        			.viewCnt(post.getViewCnt())
        			.build();
        }
    }

    @Data
    @Builder
    public static class PostDetail {
        private int postId;     // 글 id
        private int viewCnt;      // 조회 수
        private int userId;
        private String name;     // 작성 유저
        private String title;   // 글 제목
        private String content; // 글 내용
        private LocalDateTime createdAt; // 글 작성 시간
        private LocalDateTime modifyedAt; // 글 수정 시간
        
        public static PostDetail from(Post post) {
        	return PostDetail.builder()
        			.postId(post.getPostId())
        			.userId(post.getUser().getUserId())
        			.viewCnt(post.getViewCnt())
        			.name(post.getUser().getName())
        			.title(post.getTitle())
        			.content(post.getContent())
        			.createdAt(post.getCreatedAt())
        			.modifyedAt(post.getModifyedAt())
        			.build();
        }
    }
    
    @Data
    public static class UpdateRequest {
    	private int postId;   
        private String title;   
        private String content;     
    }
}
