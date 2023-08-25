package com.company.billaris2.config;

import com.company.billaris2.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

import static com.company.billaris2.models.Permission.*;
import static com.company.billaris2.models.Role.ADMIN;
import static com.company.billaris2.models.Role.MANAGER;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {



    private final AuthenticationProvider authenticationProvider;

    private final LogoutHandler logoutHandler;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

//        configuration

        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/home/**",
                                "/messages","/companies/**","/products/**",
                                "/category/**",
                                "/auth/**",
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html"
                        )
                        .permitAll()
                        .requestMatchers("/management/**")
                            .hasAnyRole(ADMIN.name(), MANAGER.name())

                        .requestMatchers(HttpMethod.GET,"/management/**")
                            .hasAnyRole(ADMIN_READ.name(), MANAGER.name())
                        .requestMatchers(HttpMethod.POST,"/management/**")
                            .hasAnyRole(ADMIN_CREATE.name(), MANAGER.name())
                        .requestMatchers(HttpMethod.PUT,"/management/**")
                            .hasAnyRole(ADMIN_UPDATE.name(), MANAGER.name())
                        .requestMatchers(HttpMethod.DELETE,"/management/**")
                            .hasAnyRole(ADMIN_DELETE.name(), MANAGER.name())

                        .requestMatchers(HttpMethod.GET,"/admin/**")
                        .hasAnyRole(ADMIN_READ.name(), MANAGER.name())

                        .anyRequest()
                            .authenticated()
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler(((request, response, authentication) -> SecurityContextHolder.clearContext()))
                )


        ;
        return http.build();

    }


 }
