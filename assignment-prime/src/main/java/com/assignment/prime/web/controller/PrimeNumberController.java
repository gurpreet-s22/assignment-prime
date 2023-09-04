package com.assignment.prime.web.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.prime.service.PrimeNumberCalculatorService;
import com.assignment.prime.service.exception.PrimeNumberServiceException;

@RestController
@RequestMapping(value = "/primes")
public class PrimeNumberController {

	private final PrimeNumberCalculatorService primeCalculatorService;

	public PrimeNumberController(final PrimeNumberCalculatorService primeCalculatorService) {
		this.primeCalculatorService = primeCalculatorService;
	}

	@GetMapping(value = { "/{value}" }, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public PrimeNumbers generatePrime(@PathVariable final int value,
			@RequestParam(required = false, name = "algo") final String algoName) throws PrimeNumberServiceException {

		final List<Integer> primeList = primeCalculatorService.getPrimeNumbers(value, algoName);

		final int primes[] = primeList != null ? primeList.stream().mapToInt(i -> i).toArray() : new int[0];

		final PrimeNumbers response = new PrimeNumbers(value, primes);

		return response;
	}

}
