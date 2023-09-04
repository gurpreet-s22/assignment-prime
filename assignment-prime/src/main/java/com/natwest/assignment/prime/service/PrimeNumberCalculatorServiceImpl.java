package com.natwest.assignment.prime.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.natwest.assignment.prime.persistence.PrimeNumberDao;
import com.natwest.assignment.prime.persistence.PrimeNumberModel;
import com.natwest.assignment.prime.service.exception.PrimeNumberServiceException;
import com.natwest.assignment.prime.util.ResourceBundleService;

@Service
public class PrimeNumberCalculatorServiceImpl implements PrimeNumberCalculatorService {

	private final Map<PrimeNumberAlgo.AlgoTypes, PrimeNumberAlgo> algoTypes;

	private final PrimeNumberDao primeNumbersDao;

	private final ResourceBundleService resourceBundle;

	public PrimeNumberCalculatorServiceImpl(final Map<PrimeNumberAlgo.AlgoTypes, PrimeNumberAlgo> algoTypes,
			final PrimeNumberDao primeNumbersDao, final ResourceBundleService resourceBundle) {
		this.algoTypes = algoTypes;
		this.primeNumbersDao = primeNumbersDao;
		this.resourceBundle = resourceBundle;
	}

	@Override
	public List<Integer> getPrimeNumbers(final int value, final String algoName) throws PrimeNumberServiceException {

		final PrimeNumberAlgo algo;
		if (StringUtils.isEmpty(algoName)) {
			algo = algoTypes.get(PrimeNumberAlgo.AlgoTypes.ONE);
		} else {

			Optional<PrimeNumberAlgo.AlgoTypes> algoType = Arrays.stream(PrimeNumberAlgo.AlgoTypes.values())
					.filter(element -> element.name().equalsIgnoreCase(algoName)).findAny();

			if (!algoType.isPresent()) {

				throw new PrimeNumberServiceException(
						resourceBundle.getMessage("PRIME_NUMBER_ALGO_NOT_FOUND", algoName));

			}
			algo = algoTypes.get(algoType.get());
		}

		final PrimeNumberModel fromCache = primeNumbersDao.get(value);

		if (fromCache != null) {
			return fromCache.getPrimeNumbers();
		} else {

			final PrimeNumberModel savePrimes = new PrimeNumberModel(value, algo.calculatePrimes(value));

			primeNumbersDao.save(savePrimes);

			return savePrimes.getPrimeNumbers();
		}

	}

}
