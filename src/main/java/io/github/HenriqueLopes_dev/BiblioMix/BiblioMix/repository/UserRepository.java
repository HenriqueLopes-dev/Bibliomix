package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.StdUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<StdUser, UUID> {
    Optional<StdUser> findByEmail(String email);
}
