package com.github.riese.rafael.mystreet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "state")
public class State extends Auditable implements IEntity {
    @Id
    private String id;
    @Indexed(unique=true)
    @Size(min = 1, max = 255, message = "Estado deve ter entre {min} e {max} caracteres")
    private String name;
}
