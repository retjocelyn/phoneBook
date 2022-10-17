package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private CustomLogoutHandler logoutHandler;

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Configuration de toutes les urls utilisées
        http
                .authorizeHttpRequests()
//                .antMatchers("/books/add").permitAll()
//                .antMatchers("/books/edit").permitAll()
//                .antMatchers("/books/delete").permitAll()
                .antMatchers("/users/register").permitAll()
                // Configure les autres urls
                .anyRequest().authenticated()

                // Configuration du login
                .and()
                .formLogin()
                .loginPage("/users/signin")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/users/hello", true)
                .permitAll()

                // Configuration du logout
                .and()
                .logout()
                .logoutUrl("/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                .permitAll();


        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "img/**", "/favicon.ico");
    }


}
