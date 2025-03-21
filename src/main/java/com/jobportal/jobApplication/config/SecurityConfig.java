package com.jobportal.jobApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security Configuration class for the application
 * This class configures Spring Security settings, authentication, and
 * authorization
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  /**
   * Creates a PasswordEncoder bean for password hashing
   * Uses BCrypt algorithm for secure password storage
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Creates an AuthenticationManager bean
   * This is used to authenticate users during login
   */
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  /**
   * Creates an AuthenticationProvider bean
   * This configures how users are authenticated
   * Uses DaoAuthenticationProvider to load user details from the database
   */
  @Bean
  public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  /**
   * Configures security rules for the application
   * This includes:
   * - Disabling CSRF (since we're using JWT)
   * - Configuring which endpoints are public and which require authentication
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable()) // Disable CSRF protection
        .authorizeHttpRequests(auth -> auth
            // Allow public access to these endpoints
            .requestMatchers("/api/auth/login", "/api/users/register", "/api/companies/register").permitAll()
            // Require authentication for all other endpoints
            .anyRequest().authenticated());

    return http.build();
  }
}