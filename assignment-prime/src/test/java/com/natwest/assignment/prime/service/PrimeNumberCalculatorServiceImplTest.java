package com.natwest.assignment.prime.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.natwest.assignment.prime.persistence.PrimeNumberDao;
import com.natwest.assignment.prime.persistence.PrimeNumberModel;
import com.natwest.assignment.prime.service.exception.PrimeNumberServiceException;
import com.natwest.assignment.prime.util.ResourceBundleService;

@ExtendWith(SpringExtension.class)
@Import(ResourceBundleServiceTestConfiguration.class)
class PrimeNumberCalculatorServiceImplTest {

	private PrimeNumberCalculatorServiceImpl test;

	@Mock
	private PrimeNumberDao primeNumbersDao;

	@Autowired
	private ResourceBundleService resourceBundle;

	private Map<PrimeNumberAlgo.AlgoTypes, PrimeNumberAlgo> algoTypes;

	@BeforeEach
	void setUp() throws Exception {
		algoTypes = new HashMap<>();
		algoTypes.put(PrimeNumberAlgo.AlgoTypes.ONE, new PrimeNumberAlgoDefault());
		algoTypes.put(PrimeNumberAlgo.AlgoTypes.TWO, new PrimeNumberAlgoTwo());

		test = new PrimeNumberCalculatorServiceImpl(algoTypes, primeNumbersDao, resourceBundle);
	}

	@AfterEach
	void tearDown() throws Exception {
		test = null;
	}

	@Test
	void testGetPrimeNumbers() throws PrimeNumberServiceException {
		final List<Integer> primeListFor5 = Arrays.asList(2, 3, 5);

		when(primeNumbersDao.get(5)).thenReturn(null);
		final List<Integer> response = test.getPrimeNumbers(5, null);

		assertTrue(response.size() == primeListFor5.size() && response.containsAll(primeListFor5));
	}

	@Test
	void testGetPrimeNumbersFromCache() throws PrimeNumberServiceException {
		final List<Integer> primeListFor5 = Arrays.asList(2, 3, 5);
		final PrimeNumberModel primeNumberModel = new PrimeNumberModel(5, primeListFor5);

		when(primeNumbersDao.get(5)).thenReturn(primeNumberModel);

		final List<Integer> response = test.getPrimeNumbers(5, null);

		assertTrue(response.size() == primeListFor5.size() && response.containsAll(primeListFor5));
	}

	@Test()
	void testGetPrimeNumbersForException() throws PrimeNumberServiceException {
		final String algoName = "invalid";
		final String exceptionMsg = "Prime numbers algo does not exists with name " + algoName;
		when(primeNumbersDao.get(5)).thenReturn(null);

		final PrimeNumberServiceException exception = assertThrows(PrimeNumberServiceException.class, () -> {
			test.getPrimeNumbers(5, algoName);
		});

		assertNotNull(exception);
		assertEquals(exceptionMsg, exception.getMessage());
	}

	@Test
	void testGetPrimeNumbersWhenNotPrime() throws PrimeNumberServiceException {

		when(primeNumbersDao.get(1)).thenReturn(null);
		final List<Integer> response = test.getPrimeNumbers(1, null);

		assertTrue(response.isEmpty());
	}

}
