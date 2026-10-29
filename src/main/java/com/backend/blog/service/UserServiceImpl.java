package com.backend.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import com.backend.blog.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.blog.entity.User;
import com.backend.blog.payloads.UserDto;
import com.backend.blog.repositories.UserDao;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper modelMapper;

	public UserDto addUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user= this.dtoToUser(userDto);
		User savedUser=this.userDao.save(user);
	    return this.userToDto(savedUser);
	}

	public List<UserDto> getAllUser() {
		// TODO Auto-generated method stub
		List<User> users=this.userDao.findAll();
		List<UserDto> userDto=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDto;
	}

	public UserDto findById(long id) {
		// TODO Auto-generated method stub
		User user= this.userDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("User id is not Present"));
		return this.userToDto(user);
	}

	public UserDto updateUser(UserDto userDto, long id) {
		// TODO Auto-generated method stub
		User user=this.userDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("User id not present"));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser=this.userDao.save(user);
	    return this.userToDto(updatedUser);
	}

    	public void deleteUser(long id) {
		// TODO Auto-generated method stub
		User user= this.userDao.findById(id).orElseThrow(()->new ResourceNotFoundException("User id is not present"));
		this.userDao.delete(user);
	}
	
	private User dtoToUser(UserDto userDto) {
	     
		User user= this.modelMapper.map(userDto,User.class);
		/*user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());*/
		return user;
		
	}
	
	private UserDto userToDto(User user) {
		UserDto userDto=this.modelMapper.map(user,UserDto.class);
		
		/*userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());*/
		return userDto;
	}
}
