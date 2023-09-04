package com.assignment.prime.service.exception;

public class PrimeNumberServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public PrimeNumberServiceException(final String errorMessage, final Throwable err) {
		super(errorMessage, err);
	}

	public PrimeNumberServiceException(final String errorMessage) {
		super(errorMessage);
	}

}
