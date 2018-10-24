package com.dms.useful;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.dms.exception.ValidateException;

public class CPFTest {

	private Document document;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void devePossuir_11_Digitos() throws Exception {
		exception.expect(ValidateException.class);
		exception.expectMessage("O CPF deve possuir 11 digitos.");

		document = new CPF("7656765045");
	}

	@Test
	public void deveSerInvalido() throws Exception {
		exception.expect(ValidateException.class);
		exception.expectMessage("Não é um número de CPF válido");

		document = new CPF("76567650453");
	}

	@Test
	public void deveSerValido() throws Exception {
		document = new CPF("766.676.504-53");

		assertThat(document.getNumber(), is("76667650453"));
	}

	@Test
	public void deveObterCPFFormatado() throws Exception {
		document = new CPF("76667650453");

		assertThat(document.getNumberFormatted(), is("766.676.504-53"));
	}

	@Test
	public void deveGerarNullPointerException() {
		exception.expect(ValidateException.class);
		exception.expectMessage("Parâmetro não pode ser nulo");

		document = new CPF(null);
	}

	@Test
	public void devePossuirApenasNumero() {
		exception.expect(ValidateException.class);
		exception.expectMessage("O CPF deve possuir 11 digitos.");

		document = new CPF("76t.6t6.504-53");
	}

	@Test
	public void documentosSaoIguais() {
		Document expected = new CPF("766.676.504-53");
		document = new CPF("76667650453");

		assertThat(document, is(expected));
	}
}
