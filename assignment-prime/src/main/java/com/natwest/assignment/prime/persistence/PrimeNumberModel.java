package com.natwest.assignment.prime.persistence;

import java.util.List;

public class PrimeNumberModel {

	private final Integer value;

	private final List<Integer> primeNumbers;

	public PrimeNumberModel(final Integer value, final List<Integer> primeNumbers) {

		this.value = value;
		this.primeNumbers = primeNumbers;
	}

	public Integer getValue() {
		return value;
	}

	public List<Integer> getPrimeNumbers() {
		return primeNumbers;
	}

}
