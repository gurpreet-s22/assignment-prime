package com.assignment.prime.persistence;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.assignment.prime.persistence.InMemoryPrimeNumber;
import com.assignment.prime.persistence.LruCache;
import com.assignment.prime.persistence.PrimeNumberModel;

public class InMemoryPrimeNumberTest {

	private InMemoryPrimeNumber test;

	private LruCache<Integer, List<Integer>> lruCache;

	@BeforeEach
	void setUp() throws Exception {
		lruCache = new LruCache<>(10);
		test = new InMemoryPrimeNumber(lruCache);
	}

	@AfterEach
	void tearDown() throws Exception {
		lruCache.clear();
		lruCache = null;
		test = null;
	}

	@Test
	void test_saveData_thenGetData_andDataFound() {
		final List<Integer> primeListFor5 = Arrays.asList(2, 3, 5);
		final List<Integer> primeListFor10 = Arrays.asList(2, 3, 5, 7);

		test.save(new PrimeNumberModel(5, primeListFor5));
		test.save(new PrimeNumberModel(10, primeListFor10));

		assertTrue(test.get(5).getValue() == 5);
		assertTrue(test.get(5).getPrimeNumbers().size() == primeListFor5.size()
				&& test.get(5).getPrimeNumbers().containsAll(primeListFor5));

		assertTrue(test.get(10).getValue() == 10);
		assertTrue(test.get(10).getPrimeNumbers().size() == primeListFor10.size()
				&& test.get(10).getPrimeNumbers().containsAll(primeListFor10));

	}

	@Test
	void test_saveData_thenGetData_andDataNotFound() {

		lruCache = new LruCache<>(2);
		test = new InMemoryPrimeNumber(lruCache);

		final List<Integer> primeListFor5 = Arrays.asList(2, 3, 5);
		final List<Integer> primeListFor10 = Arrays.asList(2, 3, 5, 7);
		final List<Integer> primeListFor25 = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23);

		test.save(new PrimeNumberModel(5, primeListFor5));
		test.save(new PrimeNumberModel(10, primeListFor10));
		test.save(new PrimeNumberModel(25, primeListFor25));

		assertTrue(test.get(10).getValue() == 10);
		assertTrue(test.get(10).getPrimeNumbers().size() == primeListFor10.size()
				&& test.get(10).getPrimeNumbers().containsAll(primeListFor10));

		assertTrue(test.get(25).getValue() == 25);
		assertTrue(test.get(25).getPrimeNumbers().size() == primeListFor25.size()
				&& test.get(25).getPrimeNumbers().containsAll(primeListFor25));

		assertNull(test.get(5));

	}

}
