package com.github.riese.rafael.mystreet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "user")
public class User extends Auditable implements IEntity {
    @Id
    private String id;
    @NotNull
    @Size(min=1, max=255)
    private String name;
    @DBRef
    @NotNull
    Profile profile;
    @Indexed(unique=true)
    @NotNull
    @Size(min=1, max=255)
    private String cpfCnpj;
    @Indexed(unique=true)
    @NotNull
    @Size(min=1, max=255)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min=1, max=255)
    private String password;
}
