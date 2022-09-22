package com.github.riese.rafael.mystreet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "image")
public class Image {
    @Id
    private String id;
    @DBRef
    private Claim claim;
    private byte[] content;
    @CreatedDate
    private DateTime createdAt;
    @LastModifiedDate
    private DateTime updatedAt;
}
