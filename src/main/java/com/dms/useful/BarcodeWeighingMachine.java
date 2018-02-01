package com.dms.useful;

import java.math.BigDecimal;

import com.dms.exception.ValidateException;

/**
 * {@code BarcodeWeighingMachine} can't be invalid throws
 * {@link ValidateException}, it must have number valid.
 * 
 * @author Diorgenes Morais
 * @version 1.0.0
 * 
 */
public class BarcodeWeighingMachine extends Barcode {

	public BarcodeWeighingMachine(String number) throws ValidateException {
		super(number);
		validateCode(number);
	}

	private void validateCode(String number) throws ValidateException {
		if (number == null || !number.substring(0, 1).equals("2")) {
			throw new ValidateException("Não é um código de barras de balança");
		}
	}

	/**
	 * Get product code.
	 * 
	 * @return product code of type {@code Long}.
	 */
	public Long getProductCode() {
		return Long.parseLong(getNumber().substring(1, 5));
	}

	/**
	 * Get price per unit.
	 * 
	 * @return price per unit of type {@code BigDecimal}.
	 */
	public BigDecimal getPricePerUnit() {
		// separar os digitos e colocar um ponto: 0000000[000.00]0
		return new BigDecimal(getNumber().substring(7, 12).replaceAll("(\\d{3})(\\d)", "$1.$2"));
	}

	/**
	 * Get weight.
	 * 
	 * @return weight of type {@code Double}.
	 */
	public Double getWeight() {
		// separar os digitos e colocar um ponto: 0000000[00.000]0
		return Double.parseDouble(getNumber().substring(7, 12).replaceAll("(\\d{2})(\\d)", "$1.$2"));
	}

}
