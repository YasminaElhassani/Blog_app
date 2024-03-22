package io.novelis.novelisblogapp.dao;

import io.novelis.novelisblogapp.domains.Article;
import io.novelis.novelisblogapp.domains.Comment;
import io.novelis.novelisblogapp.service.dtos.CommentDto;
import io.novelis.novelisblogapp.service.mappers.ArticleMapper;
import io.novelis.novelisblogapp.service.mappers.CommentMapping;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CommentDao {
    private final EntityManager entityManager;
    private final CommentMapping commentMapping;
    public List<CommentDto> findCommentByArticleId(Long idArticle) {
        String jpql = "SELECT c FROM Comment c JOIN c.article a WHERE a.id = :id";
        TypedQuery<Comment> query = entityManager.createQuery(jpql, Comment.class);
        query.setParameter("id", idArticle);

        List<Comment> comments = query.getResultList();

        return comments.stream()
                .map(commentMapping::toDTO)
                .collect(Collectors.toList());
    }
}
