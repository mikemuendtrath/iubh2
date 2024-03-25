package com.example.isef01projekt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
		
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {   	
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select benutzer, pw, active from sicherheit where benutzer=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select benutzer, rolle from mitglieder where benutzer=?");  
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
        	.authorizeHttpRequests(configurer -> configurer 
                                    .requestMatchers("/mitarbeiter").hasAnyRole("MANAGER", "ADMIN")
						            .requestMatchers("/projekte/save").hasAnyRole("MANAGER", "ADMIN")
						            .requestMatchers("/projekte/delete").hasRole("MANAGER")
        							.requestMatchers("/projekte/update").hasAnyRole("MANAGER", "ADMIN")
						            .requestMatchers("/projekte/anlegen").hasRole("MANAGER") 
						            .requestMatchers("/aufgaben/anlegen").hasRole("ADMIN")
						            .requestMatchers("/aufgaben/save").hasAnyRole("ADMIN", "USER")
						            .requestMatchers("/aufgaben/delete").hasRole("ADMIN")
						            .requestMatchers("/aufgaben/update").hasAnyRole("ADMIN", "USER")						            
			                        .anyRequest().authenticated())        	
            .formLogin(form -> form
		                .loginPage("/login")
		                .loginProcessingUrl("/authenticateTheUser")
		                .permitAll())
            .logout(logout -> logout.permitAll())
            .exceptionHandling(configurer -> configurer.accessDeniedPage("/error"));
        return http.build();
    }
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.debug(true).ignoring().requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
    }
}
