package com.natwest.assignment.prime.persistence;

public interface PrimeNumberDao {

	void save(final PrimeNumberModel primeNumbers);

	PrimeNumberModel get(final int value);

}
