package io.novelis.novelisblogapp.api.resources;


import io.novelis.novelisblogapp.domains.Article;
import io.novelis.novelisblogapp.service.ArticleService;
import io.novelis.novelisblogapp.service.dtos.ArticleDto;
import io.novelis.novelisblogapp.service.dtos.CommentDto;
import io.novelis.novelisblogapp.util.UserUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleResource {

    private final ArticleService articleService;
    @GetMapping("/public/allArticles")
    public List<ArticleDto> getAll() {
        return articleService.getAllArticles();
    }

    @GetMapping("/id-of-current-user")
    //@AuthenticationPrincipal UserDetails userDetails
    public List<ArticleDto> getForCurrentUser() {
        //String username = userDetails.getId();
        Long idCurrentUser = UserUtil.getIdConnectedUser();
        return articleService.getArticlesByCurrentUser(idCurrentUser);
    }
    @GetMapping("/public/{id}")
    public ResponseEntity<ArticleDto> getById(@PathVariable Long id) {
            return ResponseEntity.ok(articleService.getArticleById(id));
    }
    @GetMapping("/public")
    public Page<ArticleDto> getPaginated(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size) {
        return (Page<ArticleDto>) articleService.getPaginatedArticles(PageRequest.of(page, size));
    }
    @PostMapping
        public ResponseEntity<ArticleDto> create(@RequestBody ArticleDto articleDto) {
        ArticleDto createdArticle = articleService.createArticle(articleDto);
        return ResponseEntity.created(URI.create("/article/" + createdArticle.getId())).body(createdArticle);
    }
    @PutMapping
        public ResponseEntity<ArticleDto> update(@RequestBody @Valid  ArticleDto articleDto) {

            return ResponseEntity.ok(articleService.updateArticle(articleDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/public/search-by-kyword")
    public ResponseEntity<List<ArticleDto>> searchByKeyword(String keyword, @RequestParam(defaultValue = "false") boolean isForCurrentUser) {
        Long idCurrentUser = -1L;
        if (isForCurrentUser){
             idCurrentUser = UserUtil.getIdConnectedUser();
        }
        List<ArticleDto> articles = articleService.searchArticlesByKeyword(keyword, idCurrentUser);
        return ResponseEntity.ok(articles);
    }
}

