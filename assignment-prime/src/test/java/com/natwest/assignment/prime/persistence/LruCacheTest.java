package com.natwest.assignment.prime.persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LruCacheTest {

	@Test
	void testLruCache() {
		final LruCache<Integer, Integer> cache = new LruCache<>(5);

		for (int i = 1; i <= 5; i++) {
			cache.put(i, i);
		}

		for (int i = 1; i <= 5; i++) {
			assertTrue(cache.containsKey(i));
		}

		cache.put(6, 6);
		assertFalse(cache.containsKey(1));

		cache.get(2);

		cache.put(7, 7);

		assertFalse(cache.containsKey(3));

	}

}
