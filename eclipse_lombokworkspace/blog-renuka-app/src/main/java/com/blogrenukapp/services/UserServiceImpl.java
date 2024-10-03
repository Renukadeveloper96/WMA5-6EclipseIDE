package com.blogrenukapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogrenukapp.entity.User;
import com.blogrenukapp.exceptions.ResourceNotFoundException;
import com.blogrenukapp.payload.UserDto;
import com.blogrenukapp.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser=userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long userId) {
	
		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		
		User updatedUser=	userRepo.save(user);
		UserDto userDto1=this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Long userId) {
		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		return this.userToDto(user);
	}
	
	@Override
	public List<UserDto> getAllUsers() {
		List<User>users=this.userRepo.findAll();
		List<UserDto>userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Long userId) {
	User user=	this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
		this.userRepo.delete(user);
	}
	
	private User dtoToUser(UserDto userDto) {
		User user=modelMapper.map(userDto, User.class);
//		User user=new User();
//		user.setName(userDto.getName());
//		user.setAbout(userDto.getAbout());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setId(userDto.getId());
		return user;
	}
	
	private UserDto userToDto(User user) {
		UserDto userDto=modelMapper.map(user, UserDto.class);
//		UserDto userDto =new UserDto();
//		userDto.setName(user.getName());
//		userDto.setId(user.getId());
//		userDto.setAbout(user.getAbout());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
		return userDto;
	}

	
}
