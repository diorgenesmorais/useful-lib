package com.dms.useful;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.dms.exception.ValidateException;

public class BarcodeTest {

	private Barcode barcode;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void deveThrowsExceptionIfNull() {
		exception.expect(ValidateException.class);
		exception.expectMessage("Parâmetro não pode ser nulo");
		
		barcode = new Barcode(null);
	}

	@Test
	public void deveThrowsExceptionIfEmpty() {
		exception.expect(ValidateException.class);
		exception.expectMessage("Number out of range [8 | -- | 18]");
		
		barcode = new Barcode("");
	}

	@Test
	public void throwsExceptionIfNumberOutOfRange() throws Exception {
		exception.expect(ValidateException.class);
		exception.expectMessage("Number out of range [8 | -- | 18]");
		
		barcode = new Barcode("9876584");
	}

	@Test
	public void deveSerUmBarcodeInvalido() {
		exception.expect(ValidateException.class);
		exception.expectMessage("Number invalid barcode");
		
		barcode = new Barcode("7891100053508");
	}

	@Test
	public void deveSerUmBarcodeValido() throws Exception {
		barcode = new Barcode("7891000053508");
		
		assertThat(barcode.getNumber(), equalTo("7891000053508"));
	}

}
