package com.mongo.demo.crud.repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongo.demo.crud.entity.User;

@Repository
public class UserRepositoryImpl implements UserCustomRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	private static final String PAGE_SIZE = "pageSize";
	private static final String PAGE_NUMBER = "pageNumber";
	private static final int DEFAULT_LIMIT = 10;
	private static final int DEFAULT_OFFSET = 0;

	@Override
	public List<User> retrieveUserList(Map<String, Object> user) {
		try {
			String searchKey = (String) user.get("searchKey");
			String gender = (String) user.get("gender");
			this.setPagination(user);
			int pageSize = (Integer) user.get("pageSize");
			int pageNumber = (Integer) user.get("pageNumber");

			// Combine criteria using orOperator
			Query query = new Query();
			// Search by userName or phone
			query.addCriteria(new Criteria().orOperator(Criteria.where("userName").regex(searchKey, "i"),
					Criteria.where("phone").regex(searchKey, "i")));
			query.addCriteria(Criteria.where("gender").is(gender));
			query.limit(pageSize); // limit document size
			query.skip(pageNumber); // skip document size
			query.with(Sort.by(Sort.Direction.DESC, "userID")); // Sort by userID DESC
			return mongoTemplate.find(query, User.class);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@SuppressWarnings("null")
	public User updateUserInfo(User user) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("userID").is(user.getUserID()));
			User userInfo = mongoTemplate.findOne(query, User.class);
			if (user.getUserID().equals(userInfo.getUserID())) {
				mongoTemplate.save(user);
			}
			return user;
		} catch (Exception e) {
			throw e;
		}
	}

	private void setPagination(Map<String, Object> user) {
		if (user == null) {
			user = new LinkedHashMap<String,Object>();
		}

		int	limit		= 0;
		int	pageSize	= (int) user.get(PAGE_SIZE);
		if (pageSize <= 0) {
			limit = DEFAULT_LIMIT;
		} else {
			limit = pageSize;
		}
		user.put(PAGE_SIZE, limit);

		int	offset		= 0;
		int	pageNumber	= (int) user.get(PAGE_NUMBER);
		
		if (pageNumber <= 0) {
			offset = DEFAULT_OFFSET;
		} else {
			offset = (pageNumber - 1) * limit;
		}
		user.put(PAGE_NUMBER, offset);
	}
    
}
