package com.arham.blog.Controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.arham.blog.Payloads.ApiResponse;
import com.arham.blog.Payloads.UserDto;
import com.arham.blog.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	//Post Create User

	  @PostMapping("/")
	  public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		  UserDto createUserDto = this.userService.createUser(userDto);
		  
		  return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	  }
	
	//Put Update User
	  @PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
		
		  UserDto updateUser = this.userService.updateUser(userDto, userId);
		  return ResponseEntity.ok(updateUser);
	}
	  //ADMIN
	//Delete Delete User
	 @PreAuthorize("hasRole('ADMIN')")
	  @DeleteMapping("/{userId}")
	  public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		  
		  this.userService.deleteUser(userId);
		  
		  return new  ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
	  }
	  
	//Get User Get
	
	  @GetMapping("/")
	  public ResponseEntity<List<UserDto>> getAllUsers(){
		  
		  return ResponseEntity.ok(this.userService.getAllUsers());
	  }
	  
	  @GetMapping("/{userId}")
	  public ResponseEntity<UserDto> getSingleUsers(@PathVariable Integer userId){
		  
		  return ResponseEntity.ok(this.userService.getUserById(userId));
	  }
}
