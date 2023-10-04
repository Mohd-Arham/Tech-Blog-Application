package com.arham.blog.Services;

import java.util.List;



import com.arham.blog.Payloads.PostDto;
import com.arham.blog.Payloads.PostResponse;

public interface PostService {

	//create post
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update post
	PostDto updatePost(PostDto postDto,Integer postId);
	
	//delete post
	void deletePost(Integer postId);
	
	//get All post
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//get post by category
	PostDto  getPostById(Integer postId);
	
	//get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get All post by user
	List<PostDto> getPostByUser(Integer userId);
	
	//search postbyId
	List<PostDto> searchPosts(String keyword);
}

