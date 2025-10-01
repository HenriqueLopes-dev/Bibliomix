package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository.specs;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.StdUser;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecs {

    public static Specification<StdUser> nameLike(String name) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + (name).toLowerCase() + "%");
    }

    public static Specification<StdUser> emailEqual(String email) {
        return (root, query, cb) -> cb.equal(root.get("email"), email);
    }


}
