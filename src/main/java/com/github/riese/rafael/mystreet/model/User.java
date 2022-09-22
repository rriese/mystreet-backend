package com.github.riese.rafael.mystreet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String name;
    @DBRef
    Profile profile;
    @Indexed(unique=true)
    private String cpfCnpj;
    @Indexed(unique=true)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @CreatedDate
    private DateTime createdAt;
    @LastModifiedDate
    private DateTime updatedAt;
}
