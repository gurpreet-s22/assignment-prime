package com.assignment.prime.persistence;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache<K, V> extends LinkedHashMap<K, V> {

	private final int maxSize;

	public LruCache(final int maxSize) {
		super(maxSize, 0.75f, true);
		this.maxSize = maxSize;

	}

	@Override
	protected boolean removeEldestEntry(final Map.Entry<K, V> eldest) {
		return this.size() > maxSize;
	}
}
