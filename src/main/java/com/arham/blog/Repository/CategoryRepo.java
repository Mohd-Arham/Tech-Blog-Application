package com.arham.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arham.blog.Entites.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

	
	
}
