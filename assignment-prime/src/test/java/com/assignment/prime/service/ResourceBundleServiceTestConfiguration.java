package com.assignment.prime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.assignment.prime.util.ResourceBundleService;

@TestConfiguration
@PropertySource("classpath:application-error.properties")
public class ResourceBundleServiceTestConfiguration {

	@Bean
	@Autowired
	public ResourceBundleService resourceBundleService(final Environment resourceBundle) {
		return new ResourceBundleService(resourceBundle);
	}

}
