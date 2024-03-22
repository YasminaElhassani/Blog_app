package io.novelis.novelisblogapp.dao.repositories;

import io.novelis.novelisblogapp.domains.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
