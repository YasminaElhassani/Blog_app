package io.novelis.novelisblogapp.service.mappers;

import io.novelis.novelisblogapp.domains.Article;
import io.novelis.novelisblogapp.domains.Comment;
import io.novelis.novelisblogapp.service.dtos.ArticleDto;
import io.novelis.novelisblogapp.util.UserUtil;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ArticleMapper implements Function<Article, ArticleDto> {
    @Override
    public ArticleDto apply(Article article) {
        Long idCurrentUser = UserUtil.getIdConnectedUser();
        boolean isCreatedByCurrentUser=article.getAuthor() != null ? article.getAuthor().getId() == idCurrentUser : false;
        return new ArticleDto(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getAuthor() != null ? article.getAuthor().getFirstName() : null,
                article.getDateCreation(),
                article.getDateLastUpdate(),
                isCreatedByCurrentUser,
                article.getComments().stream()
                        .map(Comment::getId)
                        .collect(Collectors.toList())
        );
    }
    public ArticleDto mapToDto(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(article.getId());
        articleDto.setTitle(article.getTitle());
        articleDto.setContent(article.getContent());
        articleDto.setDateCreation(article.getDateCreation());
        articleDto.setDateLastUpdate(article.getDateLastUpdate());
        articleDto.setAuthor(article.getAuthor().getFirstName()+" "+article.getAuthor().getLastName());

        return articleDto;
    }

    public Article mapToEntity(ArticleDto articleDto) {
        Article article = new Article();
        article.setId(articleDto.getId());
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        return article;
    }
   /* private ArticleDto convertToDto(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(article.getId());
        articleDto.setTitle(article.getTitle());
        articleDto.setContent(article.getContent());
        return articleDto;
    }*/
}





