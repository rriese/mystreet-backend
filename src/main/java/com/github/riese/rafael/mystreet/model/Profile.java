package com.github.riese.rafael.mystreet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "profile")
public class Profile {
    @Id
    private String id;
    @Indexed(unique=true)
    private String name;
    @DBRef
    private List<Permission> permission;
    @CreatedDate
    private DateTime createdAt;
    private DateTime updatedAt;
}
