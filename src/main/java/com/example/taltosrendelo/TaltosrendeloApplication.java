package com.example.taltosrendelo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@EnableConfigurationProperties
@PropertySources({@PropertySource(value = "file:${appConf}", ignoreResourceNotFound = true)})
@SpringBootApplication
public class TaltosrendeloApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TaltosrendeloApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(TaltosrendeloApplication.class);
	}

}
