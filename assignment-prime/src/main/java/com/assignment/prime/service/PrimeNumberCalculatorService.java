package com.assignment.prime.service;

import java.util.List;

import com.assignment.prime.service.exception.PrimeNumberServiceException;

public interface PrimeNumberCalculatorService {

	List<Integer> getPrimeNumbers(int value, String algoName) throws PrimeNumberServiceException;
	
}
