package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.service;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.StdUser;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository.UserRepository;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserValidator validator;

    public void save(StdUser user) {
        validator.validate(user);
        repository.save(user);
    }

    public Optional<StdUser> findById(UUID id) {
        return repository.findById(id);
    }

    public void update(StdUser user) {
        if (user.getId() == null){
            throw new RuntimeException("Não é possível atualizar um usuário que não foi cadastrado!");
        }

        validator.validate(user);
        repository.save(user);
    }

    public void delete(StdUser user) {
        repository.delete(user);
    }
}
