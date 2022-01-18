package com.cts.filter;

import com.cts.jwt.JwtTokenService;
import com.cts.model.MutableHttpServletRequest;
import com.cts.utils.ApplicationUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtTokenService jwtTokenService;

    public JwtRequestFilter(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        boolean success = false;

        if (ApplicationUtil.checkIfPathExists(request.getRequestURI())) {
            chain.doFilter(request, response);
            success = true;
        } else {
            final String requestTokenHeader = request.getHeader("Authorization");
            String jwtToken;
            // JWT Token is in the form "Bearer token". Remove Bearer word and get
            // only the Token
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                jwtToken = requestTokenHeader.substring(7);
                try {
                    String userEmail = jwtTokenService.getUsernameFromToken(jwtToken);
                    MutableHttpServletRequest mutableHttpServletRequest = new MutableHttpServletRequest(request);
                    mutableHttpServletRequest.putHeader("email", userEmail);
                    chain.doFilter(mutableHttpServletRequest, response);
                    success = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Unable to get JWT Token");
                } catch (ExpiredJwtException e) {
                    System.out.println("JWT Token has expired");
                }
            } else {
                logger.warn("JWT Token does not begin with Bearer String");
            }
        }
        if (!success) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write("{\"message\": \"UnAuthorized Not Allowed\"}");
        }
    }

}