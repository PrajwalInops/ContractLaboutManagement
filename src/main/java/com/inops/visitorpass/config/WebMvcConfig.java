package com.inops.visitorpass.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
		/*
		 * registry.addViewController("/access-denied").setViewName("access-denied");
		 * registry.addViewController("/").setViewName("homepage");
		 * registry.addViewController("/about-us").setViewName("about-us");
		 */
    	
    	 registry.addViewController("/admin").setViewName(
    	            "forward:/admin/index.html");
    	        registry.addViewController("/user").setViewName(
    	            "forward:/user/index.html");
    	
				/*
				 * registry.addViewController("/") .setViewName("forward:/helloworld.xhtml");
				 * registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
				 */
    }
}