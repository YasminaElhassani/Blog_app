package io.novelis.novelisblogapp.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob // Utilisation de l'annotation Lob pour les grands objets
    @Column(columnDefinition = "TEXT")
    @NotEmpty(message = "Le contenu ne peut pas être vide")
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dateCreation;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dateLastUpdate;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
