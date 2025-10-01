package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.service;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.StdUser;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository.UserRepository;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository.specs.UserSpecs.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserValidator validator;
    private final PasswordEncoder encoder;

    public void save(StdUser user) {
        validator.validate(user);
        String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        repository.save(user);
    }

    public Optional<StdUser> findById(UUID id) {
        return repository.findById(id);
    }

    public Optional<StdUser> findByName(String name) {
        return repository.findByName(name);
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

    public Page<StdUser> search(
            String name,
            String email,
            Integer page,
            Integer pageSize
    ) {
        Specification<StdUser> specs = Specification.unrestricted() ;


        if (name != null){
            specs = specs.and(nameLike(name));
        }

        if (email != null){
            specs = specs.and(emailEqual(email));
        }

        Pageable pageRequest = PageRequest.of(page, pageSize);

        return repository.findAll(specs, pageRequest);
    }

    public Optional<StdUser> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
