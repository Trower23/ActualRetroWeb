package com.dws.ActualRetro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@Configuration
@Order(1)
public class RestSecurityConf extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/products/consoles/**").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/products/consoles/**").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/products/consoles/**").hasAnyRole("ADMIN", "USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/products/videogames/**").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/products/videogames/**").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/products/videogames/**").hasAnyRole("ADMIN", "USER");
        // en el anterior necesitamos comprobar que el usuario es propietario del
        // elemento antes de que se ejecute, por tanto eso podemos delegarlo en el controlador,
        // de modo que se den los permisos en la api rest
        // pero si no está autorizado al no ser un elemento suyo, no pueda eliminarlo y se le
        // devuelva un not authorized
        http.authorizeRequests().anyRequest().permitAll();
        http.csrf().disable();
        http.httpBasic();
        http.formLogin().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
}

