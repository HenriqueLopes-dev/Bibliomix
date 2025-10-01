package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID>, JpaSpecificationExecutor<Author> {
    Optional<Author> findByName(String name);
}
