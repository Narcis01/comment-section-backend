package com.naical.commentsection.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry
                        -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers("/api/v1/auth/**")
                        .permitAll()
                        .requestMatchers("/api/v1/user/update").hasAnyRole("ADMIN")
                        .requestMatchers("/api/v1/user/delete").hasAnyRole("ADMIN")
                        .requestMatchers("/api/v1/user/users").hasAnyRole("ADMIN", "MANAGER", "USER")
                        .requestMatchers("/api/v1/user/create").hasAnyRole("ADMIN")
                        .requestMatchers("/api/v1/task/save").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/api/v1/task/tasks").hasAnyRole("ADMIN", "MANAGER", "USER")
                        .requestMatchers("/api/v1/task/taskLike").hasAnyRole("ADMIN", "MANAGER", "USER")
                        .requestMatchers("/api/v1/task/delete").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/api/v1/task/update").hasAnyRole("ADMIN", "MANAGER")
                        .anyRequest()
                        .authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutUrl("/api/v1/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));



        return http.build();
    }
}
