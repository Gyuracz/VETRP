package com.example.taltosrendelo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
        "classpath:/resources/",
        "classpath:/static/", "classpath:/templates/", "classpath:/templates/layouts/", "classpath:/static/css/" };


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
            .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    // @Override
    // public void addViewControllers(ViewControllerRegistry registry){
    //     registry.addViewController("/login").setViewName("authentication/login");;
    //     registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    // }
	
}