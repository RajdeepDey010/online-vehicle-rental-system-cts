package com.cts.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

public class MutableHttpServletRequest extends HttpServletRequestWrapper {
    // holds custom header and value mapping
    private final Map<String, String> customHeaders;

<<<<<<< HEAD
    public MutableHttpServletRequest(HttpServletRequest request) {
=======
    public MutableHttpServletRequest(HttpServletRequest request){
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
        super(request);
        this.customHeaders = new HashMap<String, String>();
    }

<<<<<<< HEAD
    public void putHeader(String name, String value) {
=======
    public void putHeader(String name, String value){
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
        this.customHeaders.put(name, value);
    }

    public String getHeader(String name) {
        // check the custom headers first
        String headerValue = customHeaders.get(name);

<<<<<<< HEAD
        if (headerValue != null) {
=======
        if (headerValue != null){
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
            return headerValue;
        }
        // else return from into the original wrapped object
        return ((HttpServletRequest) getRequest()).getHeader(name);
    }

    public Enumeration<String> getHeaderNames() {
        // create a set of the custom header names
        Set<String> set = new HashSet<String>(customHeaders.keySet());

        // now add the headers from the wrapped request object
        @SuppressWarnings("unchecked")
        Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
        while (e.hasMoreElements()) {
            // add the names of the request headers into the list
            String n = e.nextElement();
            set.add(n);
        }

        // create an enumeration from the set and return
        return Collections.enumeration(set);
    }
}
