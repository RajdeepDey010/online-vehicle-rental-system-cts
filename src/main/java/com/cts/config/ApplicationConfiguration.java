package com.cts.config;

<<<<<<< HEAD
=======
import com.cts.repository.BookingStatusRepository;
import com.cts.repository.UserRepository;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
import com.cts.constants.ApplicationConstants;
import com.cts.filter.AdminFilter;
import com.cts.filter.ClientFilter;
import com.cts.filter.JwtRequestFilter;
import com.cts.jwt.JwtTokenService;
<<<<<<< HEAD
import com.cts.repository.UserRepository;
import com.cts.utils.ApplicationUtil;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
=======
import com.cts.utils.RideAvailabilityCheckThread;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
<<<<<<< HEAD
=======
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1

@Configuration
public class ApplicationConfiguration {

<<<<<<< HEAD
    private UserRepository userRepository;
    private JwtTokenService jwtTokenService;
    private ApplicationUtil applicationUtil;

    @Autowired
    public ApplicationConfiguration(UserRepository userRepository, JwtTokenService jwtTokenService, ApplicationUtil applicationUtil) {
        this.userRepository = userRepository;
        this.jwtTokenService = jwtTokenService;
        this.applicationUtil = applicationUtil;
=======
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
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
    }

    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilter() {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
<<<<<<< HEAD
        registrationBean.setFilter(new AdminFilter(new JsonMapper(), this.jwtTokenService, this.applicationUtil));
=======
        registrationBean.setFilter(new AdminFilter(this.applicationProperties, new JsonMapper()));
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
        registrationBean.addUrlPatterns(ApplicationConstants.ADMINURLPREFIX);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<ClientFilter> clientFilter() {
        FilterRegistrationBean<ClientFilter> registrationBean = new FilterRegistrationBean<>();
<<<<<<< HEAD
        registrationBean.setFilter(new ClientFilter(this.userRepository, this.jwtTokenService, new JsonMapper(), this.applicationUtil));
=======
        registrationBean.setFilter(new ClientFilter(this.applicationProperties, new JsonMapper(),
                userRepository));
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
        registrationBean.addUrlPatterns(ApplicationConstants.CLIENTURLPREFIX);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<JwtRequestFilter> jwtRequestFilter() {
        FilterRegistrationBean<JwtRequestFilter> registrationBean = new FilterRegistrationBean<>();
<<<<<<< HEAD
        registrationBean.setFilter(new JwtRequestFilter(this.jwtTokenService, this.applicationUtil, new JsonMapper()));
        registrationBean.addUrlPatterns(ApplicationConstants.CLIENTURLPREFIX,
                ApplicationConstants.ADMINURLPREFIX,
                ApplicationConstants.APIPREFIX + ApplicationConstants.VALIDATETOKEN);
=======
        registrationBean.setFilter(new JwtRequestFilter(this.jwtTokenService));
        registrationBean.addUrlPatterns(ApplicationConstants.CLIENTURLPREFIX);
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }

    @Bean
    public JsonMapper getJsonMapper() {
        return new JsonMapper();
    }
}
