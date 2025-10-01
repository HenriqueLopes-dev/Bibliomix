package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.security;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.StdUser;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginName = authentication.getName();
        String loginPassword = authentication.getCredentials().toString();

        Optional<StdUser> userFound = userService.findByName(loginName);

        if (userFound.isEmpty()){
            throw new UsernameNotFoundException("Usuário e/ou senha incorretos!!!");
        }

        String encryptedPassword = userFound.get().getPassword();

        if (!encoder.matches(loginPassword, encryptedPassword)){
            throw new UsernameNotFoundException("Usuário e/ou senha incorretos!!!");
        }

        return new CustomAuthentication(userFound.get());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
