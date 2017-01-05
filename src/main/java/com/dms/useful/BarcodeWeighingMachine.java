package com.dms.useful;

import java.math.BigDecimal;

import com.dms.exceptions.ValidateException;

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
		// são 5 digitos para o preço
		StringBuilder base = new StringBuilder();
		base.append(getNumber().substring(7, 10));
		base.append(".");
		base.append(getNumber().substring(10, 12));
		return new BigDecimal(base.toString());
	}

	/**
	 * Get weight.
	 * 
	 * @return weight of type {@code Double}.
	 */
	public Double getWeight() {
		// são 5 digitos para o peso
		StringBuilder base = new StringBuilder();
		base.append(getNumber().substring(7, 9));
		base.append(".");
		base.append(getNumber().substring(9, 12));
		return Double.parseDouble(base.toString());
	}

}
