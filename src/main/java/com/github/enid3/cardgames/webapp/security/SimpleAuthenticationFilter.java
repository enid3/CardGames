package com.github.enid3.cardgames.webapp.security;

import com.github.enid3.cardgames.webapp.data.entity.Identifiable;
import com.github.enid3.cardgames.webapp.data.entity.User;
import com.github.enid3.cardgames.webapp.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class SimpleAuthenticationFilter extends GenericFilterBean {
    private final UserRepository userRepository;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String name = ((HttpServletRequest) request).getHeader(HttpHeaders.AUTHORIZATION);
        if(name != null) {
            Optional<User> userOptional = userRepository.findByName(name);
            User user = userOptional.orElseGet(() -> userRepository.save(new User(name, 1000l, 0l)));

            Authentication auth = new AbstractAuthenticationToken(AuthorityUtils.NO_AUTHORITIES) {
                @Override
                public Object getCredentials() {
                    return name;
                }

                @Override
                public Identifiable getPrincipal() {
                    return (Identifiable) user;
                }
            };
            auth.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(request, response);


    }
}
