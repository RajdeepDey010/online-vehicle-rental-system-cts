package com.cts.port;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class YourApplication {

    public static void main(String[] args) {
        SpringApplication.run(YourApplication.class, args);
    }

    @Component
    public static class ServerPortListener implements ApplicationListener<WebServerInitializedEvent> {

        @Override
        public void onApplicationEvent(WebServerInitializedEvent event) {
            int port = event.getWebServer().getPort();
            System.out.println("Application started on port: " + port);
        }
    }
}