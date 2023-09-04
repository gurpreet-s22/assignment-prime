package com.natwest.assignment.prime.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PrimeNumberAlgoTwo implements PrimeNumberAlgo {

	
	@Override
	public List<Integer> calculatePrimes(final int value) {

		final ArrayList<Integer> primeNumbers = new ArrayList<Integer>();

		if (value <= 1) {
			return primeNumbers;
		}

		for (int i = 2; i <= value; i++) {

			if (isPrime(i)) {
				primeNumbers.add(i);
			}

		}

		return primeNumbers;
	}

	private boolean isPrime(int value) {

		for (int j = 2; j <= value / 2; j++) {

			if (value % j == 0) {
				return false;
			}

		}

		return true;
	}

	@Override
	public AlgoTypes algoType() {
		return AlgoTypes.TWO;
	}

}
