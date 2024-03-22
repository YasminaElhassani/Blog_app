package io.novelis.novelisblogapp.domains;

import io.novelis.novelisblogapp.service.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.mapstruct.ap.internal.model.GeneratedType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor @ToString
public class Author extends User{
    @Enumerated(EnumType.STRING)
    private Role role = Role.AUTHER;
}
