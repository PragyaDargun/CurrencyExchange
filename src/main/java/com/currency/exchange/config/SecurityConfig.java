package com.currency.exchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig.
 */
@Configuration
public class SecurityConfig {

    /**
     * Configuration.
     *
     * @param http
     * @return SecurityFilterChain
     *
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(
                                        "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                                .anyRequest().authenticated()

                        // All requests require authentication
                )
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(
                        exceptionHandling ->
                                exceptionHandling.authenticationEntryPoint(
                                        ((request, response, authException) -> {
                                            response.sendError(
                                                    HttpStatus.UNAUTHORIZED.value(),
                                                    "Unauthorized");
                                        })));

        // Disable CSRF for APIs (optional)
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    /**
     * To manage user creds.
     *
     * @param passwordEncoder - to encode password.
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService(
            final PasswordEncoder passwordEncoder) {
        // Define a user with a username "user" and password "password"
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();

        // In-memory authentication
        return new InMemoryUserDetailsManager(user);
    }

    /**
     * to encode password.
     *
     * @return PasswordEncoder Bean.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
