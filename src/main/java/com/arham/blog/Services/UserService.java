package com.arham.blog.Services;


import java.util.List;

import com.arham.blog.Payloads.UserDto;

public interface UserService  {
	
	UserDto registerNewUser(UserDto user);

	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
}
