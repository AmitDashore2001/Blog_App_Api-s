package com.backend.blog.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.blog.payloads.UserDto;
import com.backend.blog.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	public UserService userService;

	@PostMapping("/")
	public UserDto createUser(@Valid @RequestBody UserDto userDto){
		return this.userService.addUser(userDto);
	}

	@GetMapping("/{id}")
	public UserDto getById(@PathVariable long id){
		return this.userService.findById(id);
	}

	@PutMapping("/{id}")
	public UserDto updateUser(@Valid @RequestBody UserDto userDto, @PathVariable long id)
	{
		return this.userService.updateUser(userDto,id);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable long id){
		 this.userService.deleteUser(id);
		System.out.println("Deleted Sucessfully");
	}

	@GetMapping("/")
	public List<UserDto> getAllUser(){
		return this.userService.getAllUser();
	}
}
