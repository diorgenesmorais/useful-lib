package com.dms.useful;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.dms.exception.ValidateException;
import com.dms.useful.CNPJ;
import com.dms.useful.Document;

public class CNPJTest {

	private Document document;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void devePossuir_14_digitos() {
		try {
			document = new CNPJ("0614988800010");
			Assert.fail("Deveria ter lançado uma ValidateException");
		} catch (ValidateException e) {
			assertThat(e.getMessage(), is("O número deve possuir 14 digitos"));
		}
	}

	@Test
	public void deveSerInvalido() throws Exception {
		exception.expect(ValidateException.class);
		exception.expectMessage("Este número informado não é válido");

		document = new CNPJ("06.148.888/0001-05");
	}

	@Test
	public void deveSerValido() throws Exception {
		document = new CNPJ("06149888000105");

		assertThat(document.getNumber(), is("06149888000105"));
	}

	@Test
	public void deveObterFormatado() throws Exception {
		document = new CNPJ("06149888000105");

		assertThat(document.getNumberFormatted(), is("06.149.888/0001-05"));
	}

}
