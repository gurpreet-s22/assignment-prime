package com.assignment.prime.web.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assignment.prime.service.exception.PrimeNumberServiceException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class ApiExceptionHandler {

	@ExceptionHandler({ PrimeNumberServiceException.class })
	public ErrorMessage handleRequestValidationException(final Exception ex, final HttpServletRequest request) {

		final ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());

		return errorMessage;
	}

}
