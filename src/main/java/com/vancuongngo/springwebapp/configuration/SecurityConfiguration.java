package com.vancuongngo.springwebapp.configuration;

import com.vancuongngo.springwebapp.service.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(new ShaPasswordEncoder(256));

        return authenticationProvider;
    }

    @Autowired
    public void configureAuthManager(AuthenticationManagerBuilder authenticationManagerBuilder){
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
    }

    /* In order to access H2 DB console, we have to change 3 things in Spring Security, that are:
     * allow all requests to the H2 database console url ("/console/**")
     * disable CSRF protection
     * disable X-Frame-Options
     *
     * But, this changes should only be applied on development environment
    **/

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll();
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
     }
}
