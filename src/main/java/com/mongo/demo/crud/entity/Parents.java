package com.mongo.demo.crud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Parents {
    int seqNo;
    String parentName;
    String gender;
    String job;
    String phone;
    String address;
}
