package com.mongo.demo.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongo.demo.crud.entity.Book;

@Repository
public interface MongoDBRepository extends MongoRepository<Book,Integer>, CustomMyEntityRepository {}