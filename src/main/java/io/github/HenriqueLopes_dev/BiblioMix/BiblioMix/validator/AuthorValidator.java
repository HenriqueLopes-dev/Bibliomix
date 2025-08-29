package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.validator;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.exception.DuplicatedRegistryException;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Author;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorValidator {

    private final AuthorRepository repository;

    public void validate(Author author) {
        if (existsWithName(author)){
            throw new DuplicatedRegistryException("Já existe um autor cadastrado com este nome!");
        }
    }

    private boolean existsWithName(Author author) {
        Optional<Author> opAuthor = repository.findByName(author.getName());

        if (opAuthor.isEmpty()){
            return true;
        }
        return !opAuthor.get().getId().equals(author.getId());
    }
}
