package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.service;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Book;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.BookStatus;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository.BookRepository;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.security.SecurityService;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.validator.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository.specs.BookSpecs.*;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookValidator validator;
    private final SecurityService securityService;

    public void save(Book book) {
        validator.validate(book);
        book.setOwner(securityService.getLoggedUser());
        repository.save(book);
    }

    public Optional<Book> findById(UUID id) {
        return repository.findById(id);
    }

    public void update(Book book) {
        if (book.getId() == null){
            throw new RuntimeException("Não é possivel atualizar um livro que não foi salvo ainda.");
        }

        validator.validate(book);
        repository.save(book);
    }

    public void delete(Book book) {
        repository.delete(book);
    }

    public Page<Book> search(
            String title,
            LocalDate publishedDate,
            BookStatus status,
            String isbn,
            Integer page,
            Integer pageSize
    ) {
        Specification<Book> specs = Specification.unrestricted() ;

        if (title != null){
            specs = specs.and(titleLike(title));
        }

        if (publishedDate != null){
            specs = specs.and(publishedDateEqual(publishedDate));
        }

        if (status != null){
            specs = specs.and(statusEqual(status));
        }

        if (isbn != null){
            specs = specs.and(isbnEqual(isbn));
        }


        Pageable pageRequest = PageRequest.of(page, pageSize);

        return repository.findAll(specs, pageRequest);
    }
}
