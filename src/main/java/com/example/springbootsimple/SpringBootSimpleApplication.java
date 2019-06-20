package com.example.springbootsimple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.List;

@SpringBootApplication
public class SpringBootSimpleApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringBootSimpleApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSimpleApplication.class, args);
    }

    @Value("${myapp.server-ip}")
    String serverIp;

    @Autowired
    MyAppProperties props;

    @Bean
    CommandLineRunner values() {
        return args -> {
            log.info(" > The Server IP is: " + serverIp);
            log.info(" > App Server: " + serverIp);
            log.info(" > App Name: " + props.getName());
            log.info(" > App Info: " + props.getDescription());
        };
    }

    @Component
    @ConfigurationProperties(prefix = "myapp")
    public static class MyAppProperties {
        private String name;
        private String description;
        private String serverIp;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getServerIp() {
            return serverIp;
        }

        public void setServerIp(String serverIp) {
            this.serverIp = serverIp;
        }
    }

}

