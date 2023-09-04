package com.assignment.prime.web.controller;

public class PrimeNumbers {

	private final int Initial;

	private final int Primes[];

	public PrimeNumbers(final int initial, final int primes[]) {
		this.Initial = initial;
		this.Primes = primes;
	}

	public int getInitial() {
		return Initial;
	}

	public int[] getPrimes() {
		return Primes;
	}

}
