package com.mongo.demo.crud.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "book")
public class Book {

    public Book(){}
    
    private Integer id;
    private String name;
    private String authorName;
}
