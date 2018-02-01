package com.dms.useful;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dms.exception.ValidateException;
import com.dms.useful.Barcode;

public class BarcodeTest {

	private Barcode barcode;

	@Test(expected = NullPointerException.class)
	public void deveThrowsExceptionIfNull() throws Exception {
		barcode = new Barcode(null);
		fail("deve lançar uma exceção: Null Number");
	}

	@Test(expected = ValidateException.class)
	public void deveThrowsExceptionIfEmpty() throws Exception {
		barcode = new Barcode("");
		fail("deve lançar uma exceção: Number out of range [8 | -- | 18]");
	}

	@Test(expected = ValidateException.class)
	public void throwsExceptionIfNumberOutOfRange() throws Exception {
		barcode = new Barcode("9876584");
		fail("deve lançar uma exceção: Number out of range [8 | -- | 18]");
	}

	@Test(expected = ValidateException.class)
	public void deveSerUmBarcodeInvalido() throws Exception {
		barcode = new Barcode("7891100053508");
		fail("deve lançar uma exceção: Number invalid barcode");
	}

	@Test
	public void deveSerUmBarcodeValido() throws Exception {
		barcode = new Barcode("7891000053508");
		assertEquals("7891000053508", barcode.getNumber());
	}

}
