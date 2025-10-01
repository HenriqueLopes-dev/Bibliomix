package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.security;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.StdUser;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService service;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<StdUser> user = service.findByName(username);

        if (user.isEmpty()){
            throw new UsernameNotFoundException("Usuario não encontrado!");
        }

        return User.builder()
                .username(user.get().getName())
                .password(user.get().getPassword())
                .roles(user.get().getRoles().toArray(new String[user.get().getRoles().size()]))
                .build();
    }
}

