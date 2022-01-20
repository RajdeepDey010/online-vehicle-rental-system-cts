package com.cts.config;

import com.cts.constants.ApplicationConstants;
import com.cts.filter.AdminFilter;
import com.cts.filter.ClientFilter;
import com.cts.filter.JwtRequestFilter;
import com.cts.jwt.JwtTokenService;
import com.cts.repository.UserRepository;
import com.cts.utils.ApplicationUtil;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class ApplicationConfiguration {

    private UserRepository userRepository;
    private JwtTokenService jwtTokenService;
    private ApplicationUtil applicationUtil;

    @Autowired
    public ApplicationConfiguration(UserRepository userRepository, JwtTokenService jwtTokenService, ApplicationUtil applicationUtil) {
        this.userRepository = userRepository;
        this.jwtTokenService = jwtTokenService;
        this.applicationUtil = applicationUtil;
    }

    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilter() {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminFilter(new JsonMapper(), this.jwtTokenService, this.applicationUtil));
        registrationBean.addUrlPatterns(ApplicationConstants.ADMINURLPREFIX);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<ClientFilter> clientFilter() {
        FilterRegistrationBean<ClientFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ClientFilter(this.userRepository, this.jwtTokenService, new JsonMapper(), this.applicationUtil));
        registrationBean.addUrlPatterns(ApplicationConstants.CLIENTURLPREFIX);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<JwtRequestFilter> jwtRequestFilter() {
        FilterRegistrationBean<JwtRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtRequestFilter(this.jwtTokenService, this.applicationUtil, new JsonMapper()));
        registrationBean.addUrlPatterns(ApplicationConstants.CLIENTURLPREFIX,
                ApplicationConstants.ADMINURLPREFIX,
                ApplicationConstants.APIPREFIX + ApplicationConstants.VALIDATETOKEN);
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }

    @Bean
    public JsonMapper getJsonMapper() {
        return new JsonMapper();
    }
}
