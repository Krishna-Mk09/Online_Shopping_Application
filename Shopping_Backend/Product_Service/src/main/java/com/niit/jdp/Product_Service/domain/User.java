package com.niit.jdp.Product_Service.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User {
    @Id
    private String email;
    private String firstName, lastName;
    private long phoneNumber;

    private List<Product> products;
}
