package com.uhu.agi.mongodb.yelp.project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Configuration
public class SecurityConfiguration 
{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
        return http.build();
    }
}
