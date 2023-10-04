package com.arham.blog.Controllers;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arham.blog.Entites.User;
import com.arham.blog.Exceptions.ApiException;
import com.arham.blog.Payloads.JwtAuthRequest;
import com.arham.blog.Payloads.JwtAuthResponse;
import com.arham.blog.Payloads.UserDto;
import com.arham.blog.Repository.UserRepo;
import com.arham.blog.Services.UserService;
import com.arham.blog.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth/")

public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userservice;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
		System.out.println("MOHD ARHAM AARHA HAI");
		this.authenticate(request.getUsername(),request.getPassword());
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		
		String token = this.jwtTokenHelper.generateToken(userDetails);
		
	
		JwtAuthResponse response=new JwtAuthResponse();
		
		response.setToken(token);
		
		response.setUser(this.mapper.map((User)userDetails,UserDto.class));
		
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
		
	try {
		
		this.authenticationManager.authenticate(authenticationToken);
		
	}
	catch(BadCredentialsException e) {
		System.out.println("Invalid Details !!");
		throw new ApiException("Invalid Username or Password!");
	}
		
	}
	
	//register new user api
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
		
	UserDto registeredUser = this.userservice.registerNewUser(userDto);
	return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
	}
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper mapper;

	@GetMapping("/current-user/")
	public ResponseEntity<UserDto> getUser(Principal principal) {
		User user = this.userRepo.findByEmail(principal.getName()).get();
		return new ResponseEntity<UserDto>(this.mapper.map(user, UserDto.class), HttpStatus.OK);
	}
	
		
}
