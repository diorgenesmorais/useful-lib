package com.dms.useful.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dms.useful.UFBrasil;

public class UFBrasilTest {

	@Test
	public void deveTerTotalDeEstados() throws Exception {
		int expected = 27;
		assertEquals(expected, UFBrasil.values().length);
	}

	@Test
	public void deveObterACapitalDoEstado() throws Exception {
		String expected = "Recife";
		assertEquals(expected, UFBrasil.PE.getCapital());
	}

}
