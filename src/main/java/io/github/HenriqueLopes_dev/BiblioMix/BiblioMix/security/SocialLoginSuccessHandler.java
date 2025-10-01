package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.security;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.StdUser;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SocialLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws ServletException, IOException {

        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oauth2User = oAuth2AuthenticationToken.getPrincipal();

        String email = oauth2User.getAttribute("email");
        Optional<StdUser> user = userService.findByEmail(email);

        if (user.isEmpty()){
            StdUser newUser = new StdUser();
            newUser.setEmail(email);
            newUser.setName(oauth2User.getAttribute("name"));
            newUser.setRoles(List.of("USER"));
            userService.save(newUser);
            return;
        }

        authentication = new CustomAuthentication(user.get());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
