package com.cts.filter;


import com.cts.constants.ApplicationConstants;
import com.cts.entitiy.User;
import com.cts.jwt.JwtTokenService;
import com.cts.model.AuthorizationResponse;
import com.cts.repository.UserRepository;
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
import java.util.Optional;

@Slf4j
public class ClientFilter extends OncePerRequestFilter {

    private UserRepository userRepository;
    private JwtTokenService jwtTokenService;
    private JsonMapper jsonMapper;
    private ApplicationUtil applicationUtil;

    public ClientFilter(UserRepository userRepository, JwtTokenService jwtTokenService, JsonMapper jsonMapper, ApplicationUtil applicationUtil) {
        this.userRepository = userRepository;
        this.jwtTokenService = jwtTokenService;
        this.jsonMapper = jsonMapper;
        this.applicationUtil = applicationUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getMethod().equalsIgnoreCase("options")||applicationUtil.checkIfPathExists(request.getRequestURI())) {
            filterChain.doFilter(request, response);

        } else {
            // check if client rights or not
            String userType =
                    jwtTokenService.getUserTypeFromToken(request.getHeader(ApplicationConstants.JWTTOKENHEADERNAME));
            AuthorizationResponse authorizationResponse = new AuthorizationResponse();
            boolean blocked = true;
            boolean notFound = false;
            boolean permission = true;

            if (!(userType.equalsIgnoreCase("client") ||
                    userType.equalsIgnoreCase("admin"))) {
                permission = false;
                authorizationResponse.setMessage("you must be admin or client to perform this operation");
            } else {
                String email = request.getHeader("email");

                if (email != null) {
                    Optional<User> userOptional = userRepository.findByEmail(email);
                    if (userOptional.isPresent()) {
                        User user = userOptional.get();
                        if (!user.isBlocked()) {
                            blocked = false;
                            filterChain.doFilter(request, response);
                        }
                    } else {
                        notFound = true;
                    }
                } else {
                    blocked = false;
                    notFound = true;
                    log.error("user not Found");
                    authorizationResponse.setMessage("User Not Found");
                }
            }
            if (!permission) {
                log.error("user does not have proper permissions");
            } else if (blocked) {
                log.error("user Blocked cannot perform Operation");
                authorizationResponse.setMessage("User is Blocked");
            }
            if (blocked || notFound) {
                authorizationResponse.setSuccess(false);
                String unAuthorizedResponseString = jsonMapper.writeValueAsString(authorizationResponse);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().write(unAuthorizedResponseString);
                response.getWriter().flush();
            }
        }

    }
}
