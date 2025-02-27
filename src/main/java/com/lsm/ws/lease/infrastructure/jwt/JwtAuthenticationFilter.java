package com.lsm.ws.lease.infrastructure.jwt;

import com.lsm.ws.lease.domain.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtExtractor jwtExtractor;
    private final UserRepository userRepository;
    private final HandlerExceptionResolver handlerExceptionResolver;

    public JwtAuthenticationFilter(JwtExtractor jwtExtractor, UserRepository userRepository,
                                   HandlerExceptionResolver handlerExceptionResolver) {
        this.jwtExtractor = jwtExtractor;
        this.userRepository = userRepository;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) {
        try {
            var token = jwtExtractor.extractJwtFromRequest(request);
            userRepository.verifyToken(token);
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            LOG.info("Failed jwt authentication reason: {}", ex.getMessage());
            handlerExceptionResolver.resolveException(request, response, null, ex);
        }
    }
}
