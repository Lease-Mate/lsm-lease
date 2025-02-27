package com.lsm.ws.lease.configuration.rest;

import com.lsm.ws.lease.domain.user.UserRepository;
import com.lsm.ws.lease.infrastructure.jwt.JwtAuthenticationFilter;
import com.lsm.ws.lease.infrastructure.jwt.JwtExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableWebSecurity
public class SecurityConfiguration implements WebSecurityCustomizer {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(JwtExtractor jwtExtractor, HandlerExceptionResolver handlerExceptionResolver,
                                 UserRepository userRepository) {
        this.jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtExtractor, userRepository,
                                                                   handlerExceptionResolver);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
                .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.NEVER))
                .httpBasic(AbstractHttpConfigurer::disable)
                .build();
    }

    @Override
    public void customize(WebSecurity web) {
        web.ignoring()
           .requestMatchers(
                   "/swagger-ui/**",
                   "/v3/api-docs/**",
                   "/manage/**",
                   "/v1/api/lease/internal/**",
                   "/v1/api/lease/dictionary/**",
                   "/v1/api/lease/offer/available/search",
                   "/v1/api/lease/offer/*");
    }

}
