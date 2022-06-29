package com.github.enid3.cardgames.webapp.conf;

import com.github.enid3.cardgames.webapp.data.repository.UserRepository;
import com.github.enid3.cardgames.webapp.security.SimpleAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Slf4j
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepository userRepository;

    @Bean
    SecurityFilterChain springWebFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(c-> c.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .authorizeRequests(c -> c
                        //.antMatchers(HttpMethod.GET, "/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/v3/**").permitAll()
                        .anyRequest().authenticated()
                )
                // TODO i use custom filter for reusable authentication
                .addFilterBefore(new SimpleAuthenticationFilter(userRepository), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
