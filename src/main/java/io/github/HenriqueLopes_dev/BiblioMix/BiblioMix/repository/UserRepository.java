package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.StdUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<StdUser, UUID>, JpaSpecificationExecutor<StdUser> {
    Optional<StdUser> findByEmail(String email);
    Optional<StdUser> findByName(String name);
}
