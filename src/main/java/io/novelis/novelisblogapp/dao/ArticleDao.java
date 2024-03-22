package io.novelis.novelisblogapp.dao;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.novelis.novelisblogapp.dao.repositories.ArticleRepository;
import io.novelis.novelisblogapp.domains.Article;
import io.novelis.novelisblogapp.domains.QArticle;
import io.novelis.novelisblogapp.service.dtos.ArticleDto;
import io.novelis.novelisblogapp.service.mappers.ArticleMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ArticleDao {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final EntityManager entityManager;

    public List<ArticleDto> searchByKeyWord(String keyword) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QArticle qArticle=QArticle.article;
        BooleanBuilder keywordPredicate =new BooleanBuilder().and(
                qArticle.title.like("%"+keyword.toLowerCase()+"%")
                        .or(qArticle.Content.like("%"+keyword.toLowerCase()+"%")));
        System.out.println("hh");
        // Recherche des articles correspondant au prédicat
        List<Article> articles = queryFactory
                .selectFrom(qArticle)
                .where(keywordPredicate)
                .fetch();

        return articles.stream()
                .map(articleMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<ArticleDto> findArticlesByCurrentUser(Long id) {
        String jpql = "SELECT a FROM Article a JOIN a.author u WHERE u.id = :id";
        TypedQuery<Article> query = entityManager.createQuery(jpql, Article.class);
        query.setParameter("id", id);

        List<Article> articles = query.getResultList();

        return articles.stream()
                .map(articleMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<ArticleDto> searchByKeyWordAndUserId(String keyword, Long userId) {
        throw new RuntimeException("Not yet implemented");// TODO: Implementation de la methode de recherche avec keyword pour les articles d'un utilisateur
    }
}

