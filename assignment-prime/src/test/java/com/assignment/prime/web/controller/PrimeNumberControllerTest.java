package com.assignment.prime.web.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.assignment.prime.service.PrimeNumberCalculatorService;
import com.assignment.prime.service.exception.PrimeNumberServiceException;
import com.assignment.prime.web.controller.PrimeNumberController;

@WebMvcTest(value = PrimeNumberController.class)
@ExtendWith(SpringExtension.class)
public class PrimeNumberControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PrimeNumberCalculatorService service;

	@Test
	public void testPrimeNumbers() throws Exception {
		final String expectedResponse = "{\"initial\":25,\"primes\":[2,3,5,7,11,13,17,19,23]}";

		final List<Integer> list = new ArrayList<>();

		list.add(2);
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(11);
		list.add(13);
		list.add(17);
		list.add(19);
		list.add(23);

		when(service.getPrimeNumbers(25, null)).thenReturn(list);

		final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/primes/25")
				.accept(MediaType.APPLICATION_JSON);

		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		final String jsonResponse = result.getResponse().getContentAsString();

		assertEquals(200, result.getResponse().getStatus());

		assertEquals(expectedResponse, jsonResponse);

	}

	@Test
	public void testPrimeNumbersWhenException() throws Exception {
		final String expectedResponse = "{\"errorMessage\":\"error\"}";

		when(service.getPrimeNumbers(25, null)).thenThrow(new PrimeNumberServiceException("error"));

		final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/primes/25")
				.accept(MediaType.APPLICATION_JSON);

		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		final String jsonResponse = result.getResponse().getContentAsString();

		assertEquals(200, result.getResponse().getStatus());

		assertEquals(expectedResponse, jsonResponse);

	}

}
