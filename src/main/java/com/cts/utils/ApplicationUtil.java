package com.cts.utils;

import com.cts.config.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationUtil {

    private ApplicationProperties applicationProperties;

    @Autowired
    public ApplicationUtil(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public boolean checkIfPathExists(String path) {
        boolean exists = false;
        for (String url : this.applicationProperties.getUnAuthenticatedList()) {
            if (path.startsWith(url)) {
                exists = true;
                break;
            }
        }
        return exists;
    }
}
