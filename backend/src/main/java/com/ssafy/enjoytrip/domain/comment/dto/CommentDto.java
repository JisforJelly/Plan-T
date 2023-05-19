package com.ssafy.enjoytrip.domain.comment.dto;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.stream.Collectors;

import com.ssafy.enjoytrip.domain.comment.entity.Comment;

import lombok.Builder;
import lombok.Data;

@Data
public class CommentDto {
	
    @Data
    public static class EditRequest {
        private Integer postId;
        private String content;
    }

    
    @Data
    public static class CommentListView {
    	private List<CommentItem> comments;
    	
    	public static CommentListView ofList(List<Comment> comments) {
    		List<CommentItem> items = comments
    				.stream().map(CommentItem::from)
    				.collect(Collectors.toList());
    		
    		return new CommentListView(items);
    	}

		private CommentListView(List<CommentItem> collect) {
			this.comments = collect;
		}
    }
    
    @Data
    @Builder
    public static class CommentItem {
    	private Integer commentId
        private Integer postId;
        private String name;
        private String content;
        private String createdAt;
        
        public static CommentItem from(Comment comment) {
        	return CommentItem.builder()
        			.postId(comment.getPost().getPostId())
        			.name(comment.getUser().getName())
        			.content(comment.getContent())
        			.createdAt(comment.getCreatedAt().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)))
        			.build();
        }
    }
}
