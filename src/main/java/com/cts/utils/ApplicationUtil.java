package com.cts.utils;

<<<<<<< HEAD
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
=======
import java.util.Arrays;
import java.util.List;

public class ApplicationUtil {
    public static final List<String> excludedUrlPatternList =
            Arrays.asList("/api/client/vehiclesearch",
                    "/api/client/vehicle/slots");

    public static boolean checkIfPathExists(String path) {
        boolean exists = false;
        for (String url : excludedUrlPatternList) {
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
            if (path.startsWith(url)) {
                exists = true;
                break;
            }
        }
        return exists;
    }
}
