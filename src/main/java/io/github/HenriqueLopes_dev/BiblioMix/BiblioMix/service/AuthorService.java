package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.service;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Author;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository.AuthorRepository;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.validator.AuthorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;
    private final AuthorValidator validator;

    public void save(Author author) {
        validator.validate(author);
        repository.save(author);
    }

    public Optional<Author> findById(UUID id) {
        return repository.findById(id);
    }

    public void update(Author author) {
        if (author.getId() == null){
            throw new RuntimeException("Não é possível atualizar um autor que não foi cadastrado!");
        }
        validator.validate(author);
        repository.save(author);
    }

    public void delete(Author author) {
        repository.delete(author);
    }
}
