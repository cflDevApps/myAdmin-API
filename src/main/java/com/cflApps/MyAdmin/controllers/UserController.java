package com.cflApps.MyAdmin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cflApps.MyAdmin.dtos.UserDTO;
import com.cflApps.MyAdmin.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> doLogin(@RequestBody UserDTO loginData){
		return ResponseEntity.ok(userService.getUserByLogin(loginData));		
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO newUser){
		return ResponseEntity.ok(userService.saveUser(newUser));
	}

}
