package com.cts.utils;

import java.util.Arrays;
import java.util.List;

public class ApplicationUtil {
    public static final List<String> excludedUrlPatternList =
            Arrays.asList("/api/client/vehiclesearch",
                    "/api/client/vehicle/slots");

    public static boolean checkIfPathExists(String path) {
        boolean exists = false;
        for (String url : excludedUrlPatternList) {
            if (path.startsWith(url)) {
                exists = true;
                break;
            }
        }
        return exists;
    }
}
