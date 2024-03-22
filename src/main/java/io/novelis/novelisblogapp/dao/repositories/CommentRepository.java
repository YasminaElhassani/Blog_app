package io.novelis.novelisblogapp.dao.repositories;

import io.novelis.novelisblogapp.domains.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
