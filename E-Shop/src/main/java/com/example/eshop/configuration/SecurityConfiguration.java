package com.example.eshop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource securityDataSource;

    @Autowired
    public SecurityConfiguration(DataSource securityDataSource) {
        this.securityDataSource = securityDataSource;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(securityDataSource);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/products","/products/black-friday", "/orders/new")
                .hasAnyRole("USER", "ADMIN")
                .antMatchers("/products/**"
                        , "/orders/**",
                        "/black-friday/**")
                .hasAnyRole("EMPLOYEE", "ADMIN")
                .antMatchers("/users", "/users/delete/{username}","/register/employee")
                .hasRole("ADMIN")
                .antMatchers("/")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/home")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(securityDataSource);
        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
