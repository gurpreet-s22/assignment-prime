package com.natwest.assignment.prime.web.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.natwest.assignment.prime.MainApplication;

import jakarta.annotation.PostConstruct;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MainApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimeNumberControllerIntegrationTest {

	@LocalServerPort
	private int port;

	private final TestRestTemplate restTemplate = new TestRestTemplate();

	private final HttpHeaders headers = new HttpHeaders();

	private String URL = null;

	@PostConstruct
	private void buildURL() {
		URL = "http://localhost:" + port + "/assignment/primes";
	}

	@Test
	public void testPrimeNumbersWithJsonResponse() throws JSONException {
		final String expectedResponse = "{\"initial\":25,\"primes\":[2,3,5,7,11,13,17,19,23]}";

		headers.set("Accept", "application/json");

		final HttpEntity<String> entity = new HttpEntity<>(null, headers);

		final ResponseEntity<String> response = restTemplate.exchange(URL + "/25", HttpMethod.GET, entity,
				String.class);

		final String jsonResponse = response.getBody();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedResponse, jsonResponse);

	}

	@Test
	public void testPrimeNumbersWithXMLResponse() throws JSONException {
		final String expectedResponse = "<PrimeNumbers><initial>25</initial><primes><primes>2</primes><primes>3</primes><primes>5</primes><primes>7</primes><primes>11</primes><primes>13</primes><primes>17</primes><primes>19</primes><primes>23</primes></primes></PrimeNumbers>";

		headers.set("Accept", "application/xml");

		final HttpEntity<String> entity = new HttpEntity<>(null, headers);

		final ResponseEntity<String> response = restTemplate.exchange(URL + "/25", HttpMethod.GET, entity,
				String.class);

		final String jsonResponse = response.getBody();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedResponse, jsonResponse);

	}

	@Test
	public void testPrimeNumbers_WhenValidAlgoRequestParam() throws JSONException {
		final String expectedResponse = "{\"initial\":22,\"primes\":[2,3,5,7,11,13,17,19]}";

		headers.set("Accept", "application/json");

		final HttpEntity<String> entity = new HttpEntity<>(null, headers);

		final ResponseEntity<String> response = restTemplate.exchange(URL + "/22?algo=two", HttpMethod.GET, entity,
				String.class);

		final String jsonResponse = response.getBody();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedResponse, jsonResponse);

	}

	@Test
	public void testPrimeNumbers_WhenInvalidAlgoRequestParam() throws JSONException {
		final String expectedResponse = "{\"errorMessage\":\"Prime numbers algo does not exists with name anything\"}";

		headers.set("Accept", "application/json");

		final HttpEntity<String> entity = new HttpEntity<>(null, headers);

		final ResponseEntity<String> response = restTemplate.exchange(URL + "/21?algo=anything", HttpMethod.GET, entity,
				String.class);

		final String jsonResponse = response.getBody();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedResponse, jsonResponse);

	}

}
