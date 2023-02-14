package com.github.riese.rafael.mystreet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "profile")
public class Profile extends Auditable implements IEntity, GrantedAuthority {
    @Id
    private String id;
    @Indexed(unique=true)
    @NotNull
    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
