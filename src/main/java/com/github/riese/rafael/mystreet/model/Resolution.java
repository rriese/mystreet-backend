package com.github.riese.rafael.mystreet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "resolution")
public class Resolution extends Auditable implements IEntity {
    @Id
    private String id;
    @Size(min=5, max=1000, message = "Descrição deve ter entre {min} e {max} caracteres")
    private String description;
    @Indexed(unique = true)
    @DBRef
    private Claim claim;
}
