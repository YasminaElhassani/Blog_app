package io.novelis.novelisblogapp.dao.repositories;

import io.novelis.novelisblogapp.domains.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
