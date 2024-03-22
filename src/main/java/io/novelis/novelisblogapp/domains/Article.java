package io.novelis.novelisblogapp.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200)
    @NotEmpty(message = "Le titre ne peut pas être vide")
    private String title;
    @Lob // Utilisation de l'annotation Lob pour les grands objets
    @Column(columnDefinition = "TEXT")  //validation
    @NotEmpty(message = "Le contenu ne peut pas être vide")
    private String Content;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dateCreation;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dateLastUpdate;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

}
