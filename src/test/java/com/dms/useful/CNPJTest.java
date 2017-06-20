package com.dms.useful;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dms.exceptions.ValidateException;
import com.dms.useful.CNPJ;
import com.dms.useful.Document;

public class CNPJTest {

	private Document document;

	@Test(expected = ValidateException.class)
	public void devePossuir_14_digitos() throws Exception {
		document = new CNPJ("0614988800010");
		fail("Deve lançar uma exceção: O número deve possuir 14 digitos");
	}

	@Test(expected = ValidateException.class)
	public void deveSerInvalido() throws Exception {
		document = new CNPJ("06.148.888/0001-05");
		fail("Deve lançar uma exceção: Este número informado não é válido");
	}

	@Test
	public void deveSerValido() throws Exception {
		document = new CNPJ("06149888000105");
		assertEquals("06149888000105", document.getNumber());
	}

	@Test
	public void deveObterFormatado() throws Exception {
		document = new CNPJ("06149888000105");
		assertEquals("06.149.888/0001-05", document.getNumberFormatted());
	}

}
