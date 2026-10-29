package com.backend.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.blog.payloads.UserDto;

@Service
public interface UserService {
	
	public UserDto addUser(UserDto userDto);
	public List<UserDto> getAllUser();
	public UserDto findById(long id);
	public UserDto updateUser(UserDto userDto, long id );
	public void deleteUser(long id);
}
