package com.github.riese.rafael.mystreet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

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
    @Size(min=5, max=255, message = "Título deve ter entre {min} e {max} caracteres!")
    private String title;
    @Size(min=5, max=1000, message = "Descrição deve ter entre {min} e {max} caracteres!")
    private String description;
    private String state;
    private String city;
    @Size(min=1, max=255, message = "Bairro deve ter entre {min} e {max} caracteres!")
    private String district;
}
