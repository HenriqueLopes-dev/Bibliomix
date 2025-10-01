package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository.specs;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Author;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class AuthorSpecs {

    public static Specification<Author> nameLike(String name) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + (name).toLowerCase() + "%");
    }

    public static Specification<Author> nationalityLike(String nationality) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("nationality")), "%" + (nationality).toLowerCase() + "%");
    }

    public static Specification<Author> dateOfBirthEqual(LocalDate dateOfBirth) {
        return (root, query, cb) -> cb.equal(root.get("dateOfBirth"), dateOfBirth);
    }

}
