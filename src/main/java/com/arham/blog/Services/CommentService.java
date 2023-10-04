package com.arham.blog.Services;

import com.arham.blog.Payloads.CommentDto;

public interface CommentService  {

	CommentDto createComment(CommentDto commnetDto,Integer postId);
	
	void deleteComment(Integer commentId);
}
