package com.mongo.demo.crud.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongo.demo.crud.entity.User;
import com.mongo.demo.crud.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void registerUser(User user) {
		try {
			userRepository.save(user);
		} catch (Exception e) {
			log.error("There are problem while registerUser");
			throw e;
		}
	}

	public User findByUserID(String userID) {
		try {
			Optional<User> user = userRepository.findByUserID(userID);
			if (!user.isPresent())
				new Exception("User ID Not Found!");
			return user.get();
		} catch (Exception e) {
			log.error("There are problem while findByUserID");
			throw e;
		}
	}

	public List<User> retrieveUserList(Map<String, Object> user) {
		try {
			return userRepository.findByUserNameLikeOrderByUserIDAsc((String) user.get("searchKey"));
		} catch (Exception e) {
			log.error("There are problem while retrieveUserList");
			throw e;
		}
	}

	public User updateUserInfo(User user) {
		try {
			Optional<User> userInfo = userRepository.findByUserID(user.getUserID());
			if (userInfo.isPresent()) {
				userRepository.save(user);
			}
			return userInfo.get();
		} catch (Exception e) {
			log.error("There are problem while updateUserInfo");
			throw e;
		}
	}

	public User updateUserInfoUsingMongoTemplate(User user) {
		try {
			return userRepository.updateUserInfo(user);
		} catch (Exception e) {
			log.error("There are problem while updateUserInfo");
			throw e;
		}
	}

	public boolean deleteUserInfo(String userID) {
		try {
			Optional<User> userInfo = userRepository.findByUserID(userID);
			if (userInfo.isPresent()) {
				userRepository.deleteById(userID);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
