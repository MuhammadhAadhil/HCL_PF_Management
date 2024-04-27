package com.pf.mgmt.web;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class ServletWebServerFactory extends TomcatServletWebServerFactory {

    public ServletWebServerFactory() {
        super();
        logger.info("WEB application - Started.");
    }
}
