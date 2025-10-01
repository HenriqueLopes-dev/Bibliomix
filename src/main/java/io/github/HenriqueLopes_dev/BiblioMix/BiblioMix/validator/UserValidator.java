package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.validator;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.exception.DuplicatedRegistryException;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.StdUser;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository repository;

    public void validate(StdUser user) {
        if (existsWithEmail(user)){
            throw new DuplicatedRegistryException("Já existe um usuario cadastrado com este email!");
        }
    }

    private boolean existsWithEmail(StdUser user) {
        Optional<StdUser> opUser = repository.findByEmail(user.getEmail());

        if (opUser.isEmpty()){
            return false;
        }

        return !opUser.get().getId().equals(user.getId());
    }
}
