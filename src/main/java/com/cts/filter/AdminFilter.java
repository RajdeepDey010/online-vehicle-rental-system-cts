package com.cts.filter;


import com.cts.config.ApplicationProperties;
import com.cts.model.UnAuthorizedResponse;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AdminFilter extends OncePerRequestFilter {

    private ApplicationProperties applicationProperties;
    private JsonMapper jsonMapper;

    public AdminFilter(ApplicationProperties applicationProperties, JsonMapper jsonMapper) {
        this.applicationProperties = applicationProperties;
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (token != null && token.equals(applicationProperties.getPassword())) {
            filterChain.doFilter(request, response);
        } else {
            log.error("Token Invalid or Empty for Admin API");

            UnAuthorizedResponse unAuthorizedResponse = new UnAuthorizedResponse();
            unAuthorizedResponse.setMessage("Token is Invalid or Empty. Admin Authentication Failed");
            String unAuthorizedResponseString = jsonMapper.writeValueAsString(unAuthorizedResponse);

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(unAuthorizedResponseString);
            response.getWriter().flush();

        }
    }
}
