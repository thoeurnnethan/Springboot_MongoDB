package com.mongo.demo.crud.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongo.demo.crud.entity.Book;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomMyEntityRepositoryImpl implements CustomMyEntityRepository{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Book> getBookByAuthorUsingMongoTemplate(String author) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("authorName").is(author));
            return mongoTemplate.find(query, Book.class);
        } catch (Exception e) {
            log.error("There are problem while getting Books By Author Name");
            throw e;
        }
    }

    @Override
    public List<Book> getBookByAuthorNameUsingMongoTemplate(String author){
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("authorName").regex(author, "i"));// i: case-insensitive, remove to use case-sensitive
            return mongoTemplate.find(query, Book.class);
        } catch (Exception e) {
            log.error("There are problem while getting Books By Author Name");
            throw e;
        }
    }
    
}