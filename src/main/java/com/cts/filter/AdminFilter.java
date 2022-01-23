package com.cts.filter;


import com.cts.constants.ApplicationConstants;
import com.cts.jwt.JwtTokenService;
import com.cts.model.AuthorizationResponse;
import com.cts.utils.ApplicationUtil;
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

    private JsonMapper jsonMapper;
    private JwtTokenService jwtTokenService;
    private ApplicationUtil applicationUtil;

    public AdminFilter(JsonMapper jsonMapper, JwtTokenService jwtTokenService,
                       ApplicationUtil applicationUtil) {
        this.jsonMapper = jsonMapper;
        this.jwtTokenService = jwtTokenService;
        this.applicationUtil = applicationUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean success = false;
        AuthorizationResponse authorizationResponse = new AuthorizationResponse();

        // check if url request for unauthenticated API
        if (request.getMethod().equalsIgnoreCase("options")||applicationUtil.checkIfPathExists(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            success = true;
        } else {
            String jwtToken = request.getHeader(ApplicationConstants.JWTTOKENHEADERNAME);
            String email = jwtTokenService.getEmailFromToken(jwtToken);
            String userType = jwtTokenService.getUserTypeFromToken(jwtToken);

            if (null == userType || userType.isEmpty()) {
                log.error("userType Invalid or Empty");
                authorizationResponse.setMessage("userType is Invalid. Admin Authentication Failed");
            } else {
                if (userType.equalsIgnoreCase("admin")) {
                    success = true;
                    filterChain.doFilter(request, response);
                } else {
                    log.error("User {} does not have admin permissions", email);
                    authorizationResponse.setMessage("User " + email + " does not have admin Permissions");

                }
            }
        }
        if (!success) {
            authorizationResponse.setSuccess(false);
            String unAuthorizedResponseString = jsonMapper.writeValueAsString(authorizationResponse);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(unAuthorizedResponseString);
            response.getWriter().flush();
        }
    }
}
