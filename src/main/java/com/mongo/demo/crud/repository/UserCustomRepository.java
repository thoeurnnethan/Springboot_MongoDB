package com.mongo.demo.crud.repository;

import java.util.List;
import java.util.Map;

import com.mongo.demo.crud.entity.User;

public interface UserCustomRepository {
	List<User> retrieveUserList(Map<String, Object> user);
	User updateUserInfo(User user);
}
