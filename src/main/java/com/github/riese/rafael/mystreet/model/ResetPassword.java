package com.github.riese.rafael.mystreet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "reset_password")
public class ResetPassword extends Auditable implements IEntity {
    @Id
    private String id;
    @NotNull
    private String email;
    @NotNull
    private String token;

}
