package com.zerogravitysolutions.digitalschool.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Profile("default")
public class LoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //log incoming request
        logger.info("Incoming request {} {}", request.getMethod(), request.getRequestURI());

        //continute processing the request
        filterChain.doFilter(request, response);

        //log outgoing response
        logger.info("Outgoing response {} for {} {}", response.getStatus(), request.getMethod(), request.getRequestURI());

        //set error status to 500 if the response satuts is > 500
        if(response.getStatus() >= HttpStatus.INTERNAL_SERVER_ERROR.value()){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
