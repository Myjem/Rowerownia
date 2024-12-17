package com.rowerownia.rowerownia.configuration;

import com.rowerownia.rowerownia.service.CustomUserDetailsService;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomUserDetailsService userDetailsService) throws Exception{
        http.authorizeHttpRequests(customizer ->
                customizer
                        .requestMatchers("api/v1/auth/worker/**").hasAuthority("WORKER")
                        .requestMatchers("api/v1/auth/user/**").hasAnyAuthority("USER", "WORKER")
                        .requestMatchers("api/v1/**").permitAll()
                        .anyRequest()
                        .authenticated()
        )
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(customizer -> customizer
                        .loginPage("/api/v1/login")
                        .successHandler(authenticationSuccessHandler(userDetailsService))
                        .failureHandler(authenticationFailureHandler(userDetailsService))
                        .permitAll()
                        .defaultSuccessUrl("/api/v1/home", true)
                        .failureUrl("/api/v1/login?error=true")
                )
                .sessionManagement(session -> session
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                        .expiredUrl("/api/v1/login")
                )
                .logout(logout -> logout
                        .logoutUrl("/api/v1/logout")
                        .logoutSuccessUrl("/api/v1/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );
        return http.build();

    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(CustomUserDetailsService userDetailsService) {
        return ((request, response, authentication) -> {
            userDetailsService.handleSuccessfulLogin(authentication.getName());
            response.sendRedirect("/api/v1/home");
        });
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(CustomUserDetailsService userDetailsService) {
        return ((request, response, exception) -> {
            userDetailsService.handleFailedLogin(request.getParameter("username"));
            response.sendRedirect("/api/v1/login?error=true");
        });
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, CustomUserDetailsService userDetailsService) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }



    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("admin"))
                        .roles("WORKER"));
    }


}

