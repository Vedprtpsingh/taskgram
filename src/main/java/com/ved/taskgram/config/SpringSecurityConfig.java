package com.ved.taskgram.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {
    private UserDetailsService userDetailsService;



    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
            .authorizeHttpRequests((authorize) -> {
//                authorize.requestMatchers(HttpMethod.POST,"/**").hasRole("ADMIN");
                authorize.anyRequest().authenticated();
            }).httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails ved = User.builder()
    //         .username("ved")
    //         .password(passwordEncoder().encode("ved"))
    //         .roles("USER")
    //         .build();
    //     UserDetails admin = User.builder()
    //         .username("admin")
    //         .password(passwordEncoder().encode("admin"))
    //         .roles("ADMIN")
    //         .build();
    //     return new InMemoryUserDetailsManager(ved, admin);
    // }
}