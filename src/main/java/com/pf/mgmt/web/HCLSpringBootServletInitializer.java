package com.pf.mgmt.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

import java.util.Properties;

@SpringBootApplication
@ImportResource({"classpath*:applicationContext.xml"})
public class HCLSpringBootServletInitializer extends SpringBootServletInitializer {
    private static Logger logger = LogManager.getLogger(HCLSpringBootServletInitializer.class);

    public static void main(String[] args) {
        SpringApplication.run(HCLSpringBootServletInitializer.class, args);
    }
}
