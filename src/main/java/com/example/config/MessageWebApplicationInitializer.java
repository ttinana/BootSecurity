/**
 * 
 */
package com.example.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author tijana.pavicic
 *
 */
public class MessageWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
	return new Class[] { RootConfiguration.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected String[] getServletMappings() {
	// TODO Auto-generated method stub
	return null;
    }

    // ... other overrides ...
}
