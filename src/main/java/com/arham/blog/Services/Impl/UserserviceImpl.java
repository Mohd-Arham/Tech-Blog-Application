package com.arham.blog.Services.Impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arham.blog.Config.AppConstants;
import com.arham.blog.Entites.Role;
import com.arham.blog.Entites.User;
import com.arham.blog.Payloads.UserDto;
import com.arham.blog.Repository.RoleRepo;
import com.arham.blog.Repository.UserRepo;
import com.arham.blog.Services.UserService;
import com.arham.blog.Exceptions.*;

@Service
public class UserserviceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
	User user=this.dtoToUser(userDto);
	User saveuser = this.userRepo.save(user);
		
	
		return this.userToDto(saveuser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
	
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		UserDto userToDto1 = this.userToDto(updatedUser);
		
		return userToDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","Id",userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = this.userRepo.findAll();
		List<UserDto> usersDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return usersDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		this.userRepo.delete(user);

	}
	
	public User dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		
		/*
		 * user.setId(userDto.getId()); user.setName(userDto.getName());
		 * user.setEmail(userDto.getEmail()); user.setAbout(userDto.getAbout());
		 * user.setPassword(userDto.getPassword());
		 */
        return user;
	}

	public UserDto userToDto(User user) {
		
		UserDto u=this.modelMapper.map(user,UserDto.class);
		
		return u;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto,User.class);
		//encoded password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//roles set
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User newUser = this.userRepo.save(user);
		return this.modelMapper.map(newUser,UserDto.class);
	}
}
