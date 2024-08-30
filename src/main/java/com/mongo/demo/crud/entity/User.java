package com.mongo.demo.crud.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "User")
public class User {

	@Id
	String userID;
	@Indexed
	String userName;
	String gender;
	String dob;
	String pob;
	String email;
	String password;
	@Indexed
	String phone;
	String address;
	List<Parents> parents;

}
