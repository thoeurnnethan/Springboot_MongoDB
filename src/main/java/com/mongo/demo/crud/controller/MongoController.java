package com.mongo.demo.crud.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.demo.crud.entity.Book;
import com.mongo.demo.crud.service.MongoDBService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MongoController {
    
    @Autowired
    private MongoDBService mongoDBService;

    @GetMapping(path = "/getAll", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
    public LinkedHashMap<String,Object> getAll(@RequestParam String authorName){
        List<Book> books = mongoDBService.findBookByAuthorUsingMongoTemplate(authorName);
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();
        data.put("totalCount", books.size());
        data.put("bookList", books);
        return data;
    }

    @GetMapping("/getById/{id}")
    public Book getById(@PathVariable("id") Integer id){
        return mongoDBService.findById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody Book student) throws Exception {
        mongoDBService.save(student);
    }

}
