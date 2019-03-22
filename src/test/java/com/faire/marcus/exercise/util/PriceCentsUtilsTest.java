package com.faire.marcus.exercise.util;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class PriceCentsUtilsTest {
	
	@InjectMocks
	private PriceCentsUtils priceCentsUtils;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSuccess() {
		String priceCents = "32566";
		BigDecimal bigDecimal = priceCentsUtils.toBigDecimal(priceCents);
		assertEquals(new BigDecimal("325.66"), bigDecimal);
	}
	
	@Test
	public void testTooShort() {
		String priceCents = "66";
		BigDecimal bigDecimal = priceCentsUtils.toBigDecimal(priceCents);
		assertEquals(null, bigDecimal);
	}
	
	@Test
	public void testNull() {
		String priceCents = null;
		BigDecimal bigDecimal = priceCentsUtils.toBigDecimal(priceCents);
		assertEquals(null, bigDecimal);
	}

}
