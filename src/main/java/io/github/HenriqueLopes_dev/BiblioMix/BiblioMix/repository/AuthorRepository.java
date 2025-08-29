package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
    Optional<Author> findByName(String name);
}
