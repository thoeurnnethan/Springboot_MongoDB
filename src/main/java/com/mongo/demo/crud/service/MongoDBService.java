package com.mongo.demo.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mongo.demo.crud.entity.Book;
import com.mongo.demo.crud.repository.MongoDBRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MongoDBService {
    
    @Autowired
    private MongoDBRepository mongoDBRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Book> findBookByAuthorUsingMongoTemplate(String authorName){
        try {
            return mongoDBRepository.getBookByAuthorNameUsingMongoTemplate(authorName);
        } catch (Exception e) {
            throw e;
        }
    }

    public String save(Book book) throws Exception{
        try {
            boolean isExist = mongoDBRepository.existsById(book.getId());
            if(!isExist){
                mongoDBRepository.save(book); 
            }else {
                log.error("Book Id already Exist");
                throw new Exception("Id Already Exist");
            }
            return HttpStatus.OK.toString();
        } catch (Exception e) {
            throw e;
        }
    }

    public Book findById(Integer id) {
        try {
            Optional<Book> book = mongoDBRepository.findById(id);
            return book.get();
        } catch (Exception e) {
            throw e;
        }
    }

}
