package com.cts.filter;

import com.cts.constants.ApplicationConstants;
import com.cts.jwt.JwtTokenService;
import com.cts.model.MutableHttpServletRequest;
import com.cts.model.AuthorizationResponse;
import com.cts.utils.ApplicationUtil;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
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
public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtTokenService jwtTokenService;
    private ApplicationUtil applicationUtil;
    private JsonMapper jsonMapper;

    public JwtRequestFilter(JwtTokenService jwtTokenService, ApplicationUtil applicationUtil, JsonMapper jsonMapper) {
        this.jwtTokenService = jwtTokenService;
        this.applicationUtil = applicationUtil;
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        boolean success = false;
        AuthorizationResponse authorizationResponse = new AuthorizationResponse();

        // check if current url is anauthenticated or not
        if (request.getMethod().equalsIgnoreCase("options")||this.applicationUtil.checkIfPathExists(request.getRequestURI())) {
            chain.doFilter(request, response);
            success = true;
        } else {
            final String requestTokenHeader = request.getHeader("Authorization");

            // JWT Token is in the form "Bearer token". Remove Bearer word and get
            // only the Token
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                String jwtToken = requestTokenHeader.substring(7);
                try {
                    if(!jwtTokenService.validateToken(jwtToken)){
                        throw new ExpiredJwtException(null,null,"Jwt Token Expired");
                    }
                    String userEmail = jwtTokenService.getEmailFromToken(jwtToken);
                    MutableHttpServletRequest mutableHttpServletRequest = new MutableHttpServletRequest(request);
                    mutableHttpServletRequest.putHeader("email", userEmail);
                    mutableHttpServletRequest.putHeader(ApplicationConstants.JWTTOKENHEADERNAME, jwtToken);
                    chain.doFilter(mutableHttpServletRequest, response);
                    success = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Unable to get JWT Token");
                    authorizationResponse.setMessage("Jwt Token Invalid");
                } catch (ExpiredJwtException e) {
                    System.out.println("JWT Token has expired");
                    authorizationResponse.setMessage("Bearer Token Expired");
                } catch (SignatureException signatureException) {
                    log.error("Signature Exception Occurred: {}", signatureException.getMessage());
                    authorizationResponse.setMessage("Bearer Token Invalid Signature");
                } catch (Exception exception) {
                    log.error("Unknown Exception Occurred: {}", exception.getMessage());
                    authorizationResponse.setMessage("Unknown Exception Occurred while Parsing Bearer Token");
                }
            } else {
                logger.warn("JWT Token does not begin with Bearer String");
                authorizationResponse.setMessage("Bearer Token Empty or Not Present in Token");
            }
        }
        if (!success) {
            if (null == authorizationResponse.getMessage()) {
                authorizationResponse.setMessage("unAuthorized Not Allowed");
            }
            authorizationResponse.setSuccess(false);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(jsonMapper.writeValueAsString(authorizationResponse));
        }
    }

}