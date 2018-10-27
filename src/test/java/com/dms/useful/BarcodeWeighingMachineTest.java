package com.dms.useful;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.dms.exception.ValidateException;

public class BarcodeWeighingMachineTest {

	private BarcodeWeighingMachine barcode;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void deveObterCodigoDoProduto() throws Exception {
		barcode = new BarcodeWeighingMachine("2000200003199");
		Long codigoDoProduto = 2L;

		assertThat(barcode.getProductCode(), equalTo(codigoDoProduto));
	}

	@Test
	public void deveObterOPrecoPorUnidade() throws Exception {
		barcode = new BarcodeWeighingMachine("2000200003199");
		BigDecimal expected = new BigDecimal("3.19");

		assertThat(barcode.getPricePerUnit(), equalTo(expected));
	}

	@Test
	public void deveObterOPesoDoProduto() throws Exception {
		barcode = new BarcodeWeighingMachine("2000200003199");
		BigDecimal expected = new BigDecimal("0.319");
		
		assertThat(barcode.getWeight(), equalTo(expected));
	}

	@Test
	public void deveInvalidar() throws Exception {
		exception.expect(ValidateException.class);
		exception.expectMessage("Não é um código de barras de balança");

		barcode = new BarcodeWeighingMachine("7891000053508");
	}
}
