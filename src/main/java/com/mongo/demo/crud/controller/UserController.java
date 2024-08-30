package com.mongo.demo.crud.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.demo.crud.entity.User;
import com.mongo.demo.crud.service.UserService;

@RestController
@RequestMapping("/mongo/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		userService.registerUser(user);
		return user;
	}

	@PostMapping("/list")
	public List<User> getAllUser(@RequestBody Map<String, Object> user) {
		return userService.retrieveUserList(user);
	}

	@GetMapping("/find/{userID}")
	public User retrieveUserInfo(@PathVariable("userID") String userID) {
		return userService.findByUserID(userID);
	}

	@GetMapping("/find")
	public User retrieveUserInfo1(@RequestParam("userID") String userID) {
		return userService.findByUserID(userID);
	}

	@PutMapping("/update")
	public User updateUserInfo(@RequestBody User user) {
		return userService.updateUserInfoUsingMongoTemplate(user);
	}

	@DeleteMapping("/delete/{userID}")
	public boolean updateUserInfo(@PathVariable String userID) {
		return userService.deleteUserInfo(userID);
	}
}
