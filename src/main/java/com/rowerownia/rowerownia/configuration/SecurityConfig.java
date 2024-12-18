package com.rowerownia.rowerownia.configuration;

import com.rowerownia.rowerownia.service.CustomUserDetailsService;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
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

    private final CustomUserDetailsService customUserDetailsService;
    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
   }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomUserDetailsService userDetailsService) throws Exception{
        http.authorizeHttpRequests(customizer ->
                customizer
                        .requestMatchers("/api/v1/auth/worker/**").permitAll()
                        .requestMatchers("/api/v1/auth/user/**").permitAll()
                        .requestMatchers("/api/v1/**").permitAll()
                        .requestMatchers("/login","/logout","/home","/**").permitAll()
                        .anyRequest()
                        .authenticated()
        )
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(customizer -> customizer
                        .loginPage("/login")
                        .successHandler(authenticationSuccessHandler(userDetailsService))
                        .failureHandler(authenticationFailureHandler(userDetailsService))
                        .permitAll()
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true")
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                        .expiredUrl("/login")
                )
                .authenticationProvider(daoAuthenticationProvider())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
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
            response.sendRedirect("/home");
        });
    }
//
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(CustomUserDetailsService userDetailsService) {
        return ((request, response, exception) -> {
            userDetailsService.handleFailedLogin(request.getParameter("username"));
            response.sendRedirect("/login?error=true");
        });
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, CustomUserDetailsService userDetailsService) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        provider.setUserDetailsService(customUserDetailsService);
        return provider;
    }







}

