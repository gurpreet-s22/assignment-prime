package com.natwest.assignment.prime.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class PrimeNumberModelTest {

	@Test
	void testPrimeNumberModel() {

		final List<Integer> primeListFor5 = Arrays.asList(2, 3, 5);

		final PrimeNumberModel obj = new PrimeNumberModel(5, primeListFor5);

		assertEquals(5, obj.getValue());

		assertTrue(obj.getValue() == 5);
		assertTrue(obj.getPrimeNumbers().size() == primeListFor5.size()
				&& obj.getPrimeNumbers().containsAll(primeListFor5));

	}

}
