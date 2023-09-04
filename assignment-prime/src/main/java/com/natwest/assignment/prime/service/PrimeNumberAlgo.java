package com.natwest.assignment.prime.service;

import java.util.List;

public interface PrimeNumberAlgo {

	enum AlgoTypes {
		ONE,
		TWO
		
	};
	
	List<Integer> calculatePrimes(int value);
	
	AlgoTypes algoType();
	
}
