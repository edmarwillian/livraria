package com.trabalho.livraria.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigurations {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf((csrf) -> csrf.disable())
        .authorizeHttpRequests((auth) -> auth
            .requestMatchers(HttpMethod.GET, "/clientes").permitAll()
            .requestMatchers(HttpMethod.GET, "/clientes/*").permitAll()

            .requestMatchers(HttpMethod.GET, "/livros").permitAll()
            .requestMatchers(HttpMethod.GET, "/livros/*").permitAll()

            .requestMatchers(HttpMethod.GET, "/pedidos").permitAll()
            .requestMatchers(HttpMethod.GET, "/pedidos/*").permitAll()

            .requestMatchers(HttpMethod.GET, "/contem").permitAll()
            .requestMatchers(HttpMethod.GET, "/contem/*").permitAll()

            .requestMatchers(HttpMethod.GET, "/estoque").permitAll()
            .requestMatchers(HttpMethod.GET, "/estoque/*").permitAll()
            .requestMatchers(HttpMethod.POST).authenticated()
            .requestMatchers(HttpMethod.PUT).authenticated()
            .requestMatchers(HttpMethod.DELETE).authenticated()

            .anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults());

        return http.build();
    }
    
    @Bean
    UserDetailsService users() {
        UserDetails user = User
    		.withUsername("admin")
    		.password("{noop}123")
    		.roles("USER").build();

        return new InMemoryUserDetailsManager(user);
    }
}