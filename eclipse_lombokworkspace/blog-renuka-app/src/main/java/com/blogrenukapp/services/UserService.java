package com.blogrenukapp.services;

import java.util.List;

import com.blogrenukapp.payload.UserDto;

public interface UserService {

	public UserDto createUser(UserDto request);
	public UserDto updateUser(UserDto userDto,Long userId);
	public UserDto getUserById(Long userId);
	public List<UserDto>getAllUsers();
	void deleteUser(Long userId);
	
}
