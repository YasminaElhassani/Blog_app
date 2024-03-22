package io.novelis.novelisblogapp.api.resources;
import io.novelis.novelisblogapp.util.UserUtil;
import org.springframework.security.core.Authentication;
import io.novelis.novelisblogapp.domains.Comment;
import io.novelis.novelisblogapp.service.CommentService;
import io.novelis.novelisblogapp.service.dtos.ArticleDto;
import io.novelis.novelisblogapp.service.dtos.CommentDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comments")
@RequiredArgsConstructor
public class CommentResource {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> create(@RequestBody CommentDto commentDto) {
       Long idCurrentUser = UserUtil.getIdConnectedUser();
         commentDto.setUserId(idCurrentUser);
        CommentDto savedComment = commentService.saveComment(commentDto);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }
    @GetMapping("/public/{commentId}")
    public ResponseEntity<CommentDto> getById(@PathVariable Long commentId) {
        CommentDto comment = commentService.getCommentById(commentId);
        return ResponseEntity.ok(comment);
    }
    @GetMapping("/public")
    public ResponseEntity<List<CommentDto>> getAll() {
        List<CommentDto> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }
    @GetMapping("/public/article/{idArticle}")
    public List<CommentDto> getByArticleId(@PathVariable Long idArticle) {
        return commentService.getCommentByArticleId(idArticle);
    }
    @PutMapping
    public ResponseEntity<CommentDto> update(@RequestBody @Valid CommentDto commentDto) {

        return ResponseEntity.ok(commentService.updateComment(commentDto));
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

}
