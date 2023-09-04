package com.natwest.assignment.prime.service;

import java.util.List;

import com.natwest.assignment.prime.service.exception.PrimeNumberServiceException;

public interface PrimeNumberCalculatorService {

	List<Integer> getPrimeNumbers(int value, String algoName) throws PrimeNumberServiceException;
	
}
