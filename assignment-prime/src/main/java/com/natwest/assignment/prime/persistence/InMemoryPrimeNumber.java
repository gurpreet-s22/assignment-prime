package com.natwest.assignment.prime.persistence;

import java.util.List;

public class InMemoryPrimeNumber implements PrimeNumberDao {

	private final LruCache<Integer, List<Integer>> lruCache;

	public InMemoryPrimeNumber(final LruCache<Integer, List<Integer>> lruCache) {
		this.lruCache = lruCache;
	}

	@Override
	public void save(final PrimeNumberModel primeNumbers) {
		lruCache.put(primeNumbers.getValue(), primeNumbers.getPrimeNumbers());
	}

	@Override
	public PrimeNumberModel get(final int value) {

		final List<Integer> list = lruCache.get(value);
		if (list != null) {
			return new PrimeNumberModel(value, list);
		}

		return null;
	}

}
