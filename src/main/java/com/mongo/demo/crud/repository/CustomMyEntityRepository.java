package com.mongo.demo.crud.repository;

import java.util.List;

import com.mongo.demo.crud.entity.Book;

public interface CustomMyEntityRepository {
    List<Book> getBookByAuthorUsingMongoTemplate(String author);
    List<Book> getBookByAuthorNameUsingMongoTemplate(String author);
}