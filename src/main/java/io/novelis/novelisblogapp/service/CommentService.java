package io.novelis.novelisblogapp.service;

import io.novelis.novelisblogapp.dao.ArticleDao;
import io.novelis.novelisblogapp.dao.CommentDao;
import io.novelis.novelisblogapp.dao.repositories.ArticleRepository;
import io.novelis.novelisblogapp.dao.repositories.CommentRepository;
import io.novelis.novelisblogapp.dao.repositories.UserRepository;
import io.novelis.novelisblogapp.domains.Article;
import io.novelis.novelisblogapp.domains.Comment;
import io.novelis.novelisblogapp.domains.User;
import io.novelis.novelisblogapp.service.dtos.ArticleDto;
import io.novelis.novelisblogapp.service.dtos.CommentDto;
import io.novelis.novelisblogapp.service.exeptions.BusinessException;
import io.novelis.novelisblogapp.service.exeptions.CommentNotFoundException;
import io.novelis.novelisblogapp.service.mappers.CommentMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentDao commentDao;
    private final ArticleDao articleDao;
    private final ArticleRepository articleRepository;

    private final CommentMapping commentMapping;
    private final UserRepository userRepository;
    public CommentDto saveComment(CommentDto commentDto) {
        Comment comment = commentMapping.toEntity(commentDto, articleDao);
        Article currentArticle = articleRepository.findById(commentDto.getArticleId()).get();
        comment.setArticle(currentArticle);
        User currentUser = userRepository.findById(commentDto.getUserId()).get();
        comment.setUser(currentUser);
        comment = commentRepository.save(comment);
        return commentMapping.toDTO(comment);
    }

    public CommentDto getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        return comment != null ? commentMapping.toDTO(comment) : null;
    }

    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(commentMapping::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<CommentDto> getCommentByArticleId(Long idArticle) {
        return commentDao.findCommentByArticleId(idArticle);
    }

    public CommentDto updateComment(CommentDto updatedCommentDto) {
        Comment comment = commentRepository.findById(updatedCommentDto.getId())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND.value(),
                        "Commentaire avec l'identifiant " + updatedCommentDto.getId() + " non trouvé", ""));

        comment.setContent(updatedCommentDto.getContent());
        comment.setDateLastUpdate(new Date());
        commentRepository.save(comment);
        return commentMapping.toDTO(comment);
    }

}




