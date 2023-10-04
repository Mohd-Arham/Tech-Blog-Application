package com.arham.blog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arham.blog.Entites.Category;
import com.arham.blog.Entites.Post;
import com.arham.blog.Entites.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	//custom finder method
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
}
