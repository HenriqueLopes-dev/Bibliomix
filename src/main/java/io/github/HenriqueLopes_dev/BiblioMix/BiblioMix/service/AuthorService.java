package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.service;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Author;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository.AuthorRepository;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.validator.AuthorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import static io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository.specs.AuthorSpecs.*;

import java.time.LocalDate;
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


    public Page<Author> search(
            String name,
            String nationality,
            LocalDate dateOfBirth,
            Integer page,
            Integer pageSize
    ) {

        Specification<Author> specs = Specification.unrestricted() ;


        if (name != null){
            specs = specs.and(nameLike(name));
        }

        if (nationality != null){
            specs = specs.and(nationalityLike(nationality));
        }

        if (dateOfBirth != null){
            specs = specs.and(dateOfBirthEqual(dateOfBirth));
        }

        Pageable pageRequest = PageRequest.of(page, pageSize);

        return repository.findAll(specs, pageRequest);
    }
}
