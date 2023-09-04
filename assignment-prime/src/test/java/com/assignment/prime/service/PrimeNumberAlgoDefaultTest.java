package com.assignment.prime.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrimeNumberAlgoDefaultTest {

	private PrimeNumberAlgoDefault test;

	@BeforeEach
	void setUp() throws Exception {
		test = new PrimeNumberAlgoDefault();
	}

	@AfterEach
	void tearDown() throws Exception {
		test = null;
	}

	@Test
	void testCalculatePrimes_ForLessThan0() {
		assertTrue(test.calculatePrimes(-1).isEmpty());
	}

	@Test
	void testCalculatePrimes_For0() {
		assertTrue(test.calculatePrimes(0).isEmpty());
	}

	@Test
	void testCalculatePrimes_For2() {
		final List<Integer> expected = Arrays.asList(2);

		final List<Integer> actual = test.calculatePrimes(2);

		assertTrue(actual.size() == expected.size() && actual.containsAll(expected));

	}

	@Test
	void testCalculatePrimes() {
		final List<Integer> expected = Arrays.asList(2, 3, 5);

		final List<Integer> actual = test.calculatePrimes(5);

		assertTrue(actual.size() == expected.size() && actual.containsAll(expected));

	}

}
