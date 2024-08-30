package com.mongo.demo.crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongo.demo.crud.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User,String>,UserCustomRepository{
	/*
	 * Spring Data usually provides, auto-generated queries out of method names.
	 * FindByX 
	 * StartingWith / endingWith 
	 * Between 
	 * Like and OrderBy
	 */

	Optional<User> findByUserID(String userID);

	public List<User> findByUserNameLikeOrderByUserIDAsc(String search);
}
