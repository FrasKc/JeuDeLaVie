package com.example.JeuDeLaVie.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@RequiredArgsConstructor
@Data
public class User {
    @Id
    private String id;
    private String username;
    private String password;
}
