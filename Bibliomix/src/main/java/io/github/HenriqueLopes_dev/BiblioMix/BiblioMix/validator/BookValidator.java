package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.validator;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Book;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookValidator {
    private final BookRepository repository;

    public void validate(Book book) {

    }
}
