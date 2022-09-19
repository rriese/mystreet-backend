package com.github.riese.rafael.mystreet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "claim")
public class Claim {
    @Id
    private String id;
    private User user;
    private Status status;
    private String title;
    private String description;
    private String state;
    private String city;
    private String district;
    private Date createdAt;
    private Date updatedAt;
}
