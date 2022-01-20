package com.cts.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
}
