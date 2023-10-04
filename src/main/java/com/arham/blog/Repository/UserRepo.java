package com.arham.blog.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arham.blog.Entites.User;

public interface UserRepo extends JpaRepository<User,Integer>{
	
	Optional<User> findByEmail(String email);

}
