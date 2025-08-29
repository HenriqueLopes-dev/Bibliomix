package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
