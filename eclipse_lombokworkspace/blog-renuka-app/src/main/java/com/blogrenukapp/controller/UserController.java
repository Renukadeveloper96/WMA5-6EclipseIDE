package com.blogrenukapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogrenukapp.payload.ApiResponse;
import com.blogrenukapp.payload.UserDto;
import com.blogrenukapp.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/createUser")
	public ResponseEntity<UserDto>createUser(@Valid @RequestBody UserDto userDto){	
		UserDto createUserDto=userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId")Long id){
		UserDto updateUserDto=userService.updateUser(userDto,id);
		return new ResponseEntity<>(updateUserDto,HttpStatus.CREATED);	
	}
	
	@DeleteMapping("/deleteByUserId/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId")Long id) {
		userService.deleteUser(id);
		return new ResponseEntity(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity<List<UserDto>>getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/getByUserId/{userId}")
	public ResponseEntity<UserDto>getUserById(@PathVariable("userId")Long id){
		return ResponseEntity.ok(this.userService.getUserById(id));
	}
	
	
}
