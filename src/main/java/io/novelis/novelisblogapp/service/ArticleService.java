package io.novelis.novelisblogapp.service;

import io.novelis.novelisblogapp.dao.ArticleDao;
import io.novelis.novelisblogapp.dao.repositories.ArticleRepository;
import io.novelis.novelisblogapp.dao.repositories.AuthorRepository;
import io.novelis.novelisblogapp.domains.Article;
import io.novelis.novelisblogapp.domains.Author;
import io.novelis.novelisblogapp.service.dtos.ArticleDto;
import io.novelis.novelisblogapp.service.exeptions.BusinessException;
import io.novelis.novelisblogapp.service.mappers.ArticleMapper;
import io.novelis.novelisblogapp.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final AuthorRepository authorRepository;
    private final ArticleDao articleDao;
    private static final long ALL_USERS  = -1;
    public List<ArticleDto> getAllArticles(){
        return articleRepository.findAll()
                .stream()
                .map(articleMapper)
                .collect(Collectors.toList());
    }
    public ArticleDto getArticleById(Long id){
        return articleRepository.findById(id)
                .map(articleMapper)
                .orElseThrow(()->new BusinessException(
                        HttpStatus.NOT_FOUND.value(),"article avec l'identifiant [%s] non trouvé".formatted(id),"zgzgzgg"
                ));
    }
    public ArticleDto createArticle(ArticleDto articleDto) {
        Article article = articleMapper.mapToEntity(articleDto);
        //article.setDateCreation(new Date());
        //article.setDateLastUpdate(new Date());
        Long idCurrentUser = UserUtil.getIdConnectedUser();
        Author currentUser = authorRepository.findById(idCurrentUser).get();
       article.setAuthor(currentUser);
        Article createdArticle = articleRepository.save(article);
        return articleMapper.mapToDto(createdArticle);
    }

    @Transactional
    public ArticleDto updateArticle(ArticleDto updatedArticleDto) {
        Article article = articleRepository.findById(updatedArticleDto.getId())
                .orElseThrow(()->new BusinessException(HttpStatus.NOT_FOUND.value(),"Article avec l'identifiant " + updatedArticleDto.getId() + " non trouvé",""));

            article.setTitle(updatedArticleDto.getTitle());
            article.setContent(updatedArticleDto.getContent());
            article.setDateLastUpdate(new Date());
            return convertToDto(article);

    }
    private ArticleDto convertToDto(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(article.getId());
        articleDto.setTitle(article.getTitle());
        articleDto.setContent(article.getContent());
        return articleDto;
    }

    public void deleteArticle(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Article avec l'identifiant " + id + " non trouvé");
        }
        articleRepository.deleteById(id);
    }
    public List<ArticleDto> searchArticlesByKeyword(String keyword, Long userId) {
        if (userId == ALL_USERS) {
            return articleDao.searchByKeyWord(keyword);
        }
        else {
            return articleDao.searchByKeyWordAndUserId(keyword, userId);
        }
    }

    public List<ArticleDto> getArticlesByCurrentUser(Long idCurrentUser) {
        return articleDao.findArticlesByCurrentUser(idCurrentUser);
    }
    public Page<ArticleDto> getPaginatedArticles(Pageable pageable) {
        Page<Article> articles = articleRepository.findAll(pageable);
        return articles.map(articleMapper::apply);
    }
}

