package com.natwest.assignment.prime.util;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application-error.properties")
public class ResourceBundleService {

	private final Environment resourceBundle;

	@Autowired
	public ResourceBundleService(final Environment resourceBundle) {
		this.resourceBundle = resourceBundle;
	}

	public String getMessage(final String messageKey, final String... args) {

		return MessageFormatter.arrayFormat(resourceBundle.getProperty(messageKey), args).getMessage();

	}
}
