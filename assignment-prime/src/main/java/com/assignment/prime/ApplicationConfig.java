package com.assignment.prime;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.assignment.prime.persistence.InMemoryPrimeNumber;
import com.assignment.prime.persistence.LruCache;
import com.assignment.prime.persistence.PrimeNumberDao;
import com.assignment.prime.service.PrimeNumberAlgo;
import com.assignment.prime.service.PrimeNumberCalculatorService;
import com.assignment.prime.service.PrimeNumberCalculatorServiceImpl;
import com.assignment.prime.util.ResourceBundleService;

@Configuration
public class ApplicationConfig {

	@Autowired
	private ResourceBundleService resourceBundleService;

	@Bean
	public PrimeNumberCalculatorService primeCalculatorService(final List<PrimeNumberAlgo> primeAlgo,
			@Qualifier("inMemoryPrimeNumbersDao") final PrimeNumberDao primeNumbersDao) {

		return new PrimeNumberCalculatorServiceImpl(
				primeAlgo.stream().collect(Collectors.toMap(PrimeNumberAlgo::algoType, Function.identity())),
				primeNumbersDao, resourceBundleService);

	}

	@Bean
	public LruCache<Integer, List<Integer>> lruCache() {
		return new LruCache<Integer, List<Integer>>(10);
	}

	@Bean(name = "inMemoryPrimeNumbersDao")
	public PrimeNumberDao primeNumbersDao(final LruCache<Integer, List<Integer>> lruCache) {
		return new InMemoryPrimeNumber(lruCache);
	}

}
