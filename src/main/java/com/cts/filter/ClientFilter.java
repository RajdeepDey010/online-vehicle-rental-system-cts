package com.cts.filter;


import com.cts.repository.UserRepository;
import com.cts.config.ApplicationProperties;
import com.cts.entities.User;
import com.cts.model.UnAuthorizedResponse;
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
    private ApplicationProperties applicationProperties;
    private JsonMapper jsonMapper;

    public ClientFilter(ApplicationProperties applicationProperties, JsonMapper jsonMapper,
                        UserRepository userRepository) {
        this.applicationProperties = applicationProperties;
        this.jsonMapper = jsonMapper;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        if (ApplicationUtil.checkIfPathExists(request.getRequestURI()))  {
            filterChain.doFilter(request, response);

        } else {
            String email = request.getHeader("email");
            UnAuthorizedResponse unAuthorizedResponse = new UnAuthorizedResponse();
            boolean blocked = true;
            boolean notFound = false;

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
                unAuthorizedResponse.setMessage("User Not Found");
            }
            if (blocked) {
                log.error("user Blocked cannot perform Operation");
                unAuthorizedResponse.setMessage("User is Blocked");
            }
            if (blocked || notFound) {
                String unAuthorizedResponseString = jsonMapper.writeValueAsString(unAuthorizedResponse);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().write(unAuthorizedResponseString);
                response.getWriter().flush();
            }
        }


    }
}
