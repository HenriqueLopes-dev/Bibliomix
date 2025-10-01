package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.security;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.StdUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

@RequiredArgsConstructor
@Getter
public class CustomAuthentication implements Authentication {

    private final StdUser user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role)).toList();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return user.getName();
    }
}
