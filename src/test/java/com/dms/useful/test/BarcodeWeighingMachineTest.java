package com.dms.useful.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dms.exceptions.ValidateException;
import com.dms.useful.BarcodeWeighingMachine;

public class BarcodeWeighingMachineTest {

	private BarcodeWeighingMachine barcode;

	@Test
	public void deveObterCodigoDoProduto() throws Exception {
		barcode = new BarcodeWeighingMachine("2000200003199");
		assertEquals("2", barcode.getProductCode().toString());
	}

	@Test
	public void deveObterOPrecoPorUnidade() throws Exception {
		barcode = new BarcodeWeighingMachine("2000200003199");
		System.out.println(barcode);
		assertEquals(3.19, barcode.getPricePerUnit().doubleValue(), 0.005);
	}

	@Test
	public void deveObterOPesoDoProduto() throws Exception {
		barcode = new BarcodeWeighingMachine("2000200003199");
		assertEquals(0.319, barcode.getWeight(), 0.005);
	}

	@Test(expected = ValidateException.class)
	public void deveInvalidar() throws Exception {
		barcode = new BarcodeWeighingMachine("7891000053508");
	}
}
