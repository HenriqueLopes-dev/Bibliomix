package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.service;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Book;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository.BookRepository;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.validator.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookValidator validator;

    public void save(Book book) {
        validator.validate(book);
        repository.save(book);
    }
}
