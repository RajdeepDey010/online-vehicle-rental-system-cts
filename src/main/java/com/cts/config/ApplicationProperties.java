package com.cts.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

<<<<<<< HEAD
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "config")
@Getter
@Setter
public class ApplicationProperties {
    private String adminPassword;
    private String merchantPassword;
    private long bookingMaxDuration;
    private List<String> unAuthenticatedList;
=======
@Configuration
@ConfigurationProperties(prefix = "admin")
@Getter
@Setter
public class ApplicationProperties {
    private String password;
    private long bookingMaxDuration;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
}
