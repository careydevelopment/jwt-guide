package com.careydevelopment.jwtguide.config;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;

@Configuration
public class SecurityApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	private static final Logger LOG = LoggerFactory.getLogger(SecurityApplicationInitializer.class);

	
    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
    	LOG.debug("Inserting filters");
        insertFilters(servletContext, new MultipartFilter());
    }
}