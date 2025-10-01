package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository.specs;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Book;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.BookStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class BookSpecs {

    public static Specification<Book> titleLike(String title) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("title")), "%" + (title).toLowerCase() + "%");
    }

    public static Specification<Book> publishedDateEqual(LocalDate publishedDate) {
        return (root, query, cb) -> cb.equal(root.get("publishedDate"), publishedDate);
    }

    public static Specification<Book> statusEqual(BookStatus status) {
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<Book> isbnEqual(String isbn) {
        return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
    }
}
