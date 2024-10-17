package com.shrey.react_springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow all paths
                .allowedOrigins("http://localhost:3001") // Your React app URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed methods
                .allowCredentials(true) // Allow credentials (cookies)
                .allowedHeaders("*") // Allow all headers
                .exposedHeaders("Authorization"); // Expose headers if needed
    }

    @Autowired
    private UserDetailsService userDetailsService;

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    //     return http.csrf(customizer -> customizer.disable()).
    //             authorizeHttpRequests(request -> request.anyRequest().authenticated()).
    //             // formLogin(Customizer.withDefaults()).
    //             httpBasic(Customizer.withDefaults()).
    //             sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)).build();
    // }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors()
                .and()
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity
                .authorizeHttpRequests(authz -> authz
                    .requestMatchers("/login", "/register").permitAll() // Allow login and register without authentication
                    .anyRequest().authenticated() // Other requests require authentication
                )
                .formLogin(Customizer.withDefaults()
                ).
                httpBasic(Customizer.withDefaults()).
                sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Sessions are created when necessary
                )
                .build();
    }
    // @Bean
    // public UserDetailsService userDetailsService(){
    //     new User1, new User2;
    //     return new InMemoryUserDetailsManager(user1, User2);
    // }

}