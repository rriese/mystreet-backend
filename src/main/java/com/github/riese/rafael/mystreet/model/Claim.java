package com.github.riese.rafael.mystreet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "claim")
public class Claim extends Auditable implements IEntity {
    @Id
    private String id;
    @DBRef
    private User user;
    @DBRef
    private Status status;
    private String title;
    private String description;
    private String state;
    private String city;
    private String district;
}
