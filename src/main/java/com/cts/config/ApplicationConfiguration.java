package com.cts.config;

import com.cts.repository.BookingStatusRepository;
import com.cts.repository.UserRepository;
import com.cts.constants.ApplicationConstants;
import com.cts.filter.AdminFilter;
import com.cts.filter.ClientFilter;
import com.cts.filter.JwtRequestFilter;
import com.cts.jwt.JwtTokenService;
import com.cts.utils.RideAvailabilityCheckThread;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class ApplicationConfiguration {

    private BookingStatusRepository bookingStatusRepository;
    private ApplicationProperties applicationProperties;
    private UserRepository userRepository;
    private JwtTokenService jwtTokenService;

    @Autowired
    public ApplicationConfiguration(BookingStatusRepository bookingStatusRepository,
                                    ApplicationProperties applicationProperties,
                                    UserRepository userRepository,
                                    JwtTokenService jwtTokenService) {
        this.bookingStatusRepository = bookingStatusRepository;
        this.applicationProperties = applicationProperties;
        this.userRepository = userRepository;
        this.jwtTokenService = jwtTokenService;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    public CommandLineRunner schedulingRunner(TaskExecutor executor) {
        return args -> executor.execute(new RideAvailabilityCheckThread(this.bookingStatusRepository));
    }

    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilter() {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminFilter(this.applicationProperties, new JsonMapper()));
        registrationBean.addUrlPatterns(ApplicationConstants.ADMINURLPREFIX);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<ClientFilter> clientFilter() {
        FilterRegistrationBean<ClientFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ClientFilter(this.applicationProperties, new JsonMapper(),
                userRepository));
        registrationBean.addUrlPatterns(ApplicationConstants.CLIENTURLPREFIX);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<JwtRequestFilter> jwtRequestFilter() {
        FilterRegistrationBean<JwtRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtRequestFilter(this.jwtTokenService));
        registrationBean.addUrlPatterns(ApplicationConstants.CLIENTURLPREFIX);
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }

    @Bean
    public JsonMapper getJsonMapper() {
        return new JsonMapper();
    }
}
