package io.novelis.novelisblogapp.service.mappers;

import io.novelis.novelisblogapp.dao.ArticleDao;
import io.novelis.novelisblogapp.domains.Article;
import io.novelis.novelisblogapp.domains.Comment;
import io.novelis.novelisblogapp.service.dtos.ArticleDto;
import io.novelis.novelisblogapp.service.dtos.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapping {

    Comment toEntity(CommentDto commentDto, ArticleDao articleDao);
    //@Mapping(target = "articleId", expression = "java(comment.getArticle().getId())")
    @Mapping(source = "comment.article.id", target = "articleId")
    @Mapping(source = "comment.user.id", target = "userId")
    CommentDto toDTO(Comment comment);
    List<CommentDto> toDto(List<Comment> Comment);
}
