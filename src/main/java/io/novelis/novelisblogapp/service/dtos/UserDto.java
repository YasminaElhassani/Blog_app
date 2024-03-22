package io.novelis.novelisblogapp.service.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data @AllArgsConstructor @NoArgsConstructor
public class UserDto implements Serializable {

    private Long id;
    private String userId;
    @Column(nullable = false, length = 30)
    @NotEmpty(message = "Le prenom ne peut pas être vide")
    private String firstName;
    @Column(nullable = false, length = 30)
    @NotEmpty(message = "Le nom ne peut pas être vide")
    private String lastName;
    @Column(nullable = false, length = 100)
    @Email(message = "L'adresse e-mail doit être valide")
    @NotEmpty(message = "L'email ne peut pas être vide")
    private String email;


}
