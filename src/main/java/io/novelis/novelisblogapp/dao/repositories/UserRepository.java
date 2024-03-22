package io.novelis.novelisblogapp.dao.repositories;

import io.novelis.novelisblogapp.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
