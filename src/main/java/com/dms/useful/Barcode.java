package com.dms.useful;

import com.dms.exception.ValidateException;

/**
 * {@code Barcode} can't be invalid throws {@link ValidateException}, it must
 * have number valid.
 * 
 * @author Diorgenes Morais
 * @version 1.0.1
 */
public class Barcode {

	private final String number;

	public Barcode(String number) throws ValidateException {
		if(number == null) {
			throw new ValidateException("Parâmetro não pode ser nulo");
		}
		this.number = getCodeValid(number.replaceAll("\\.|-|/", ""));
	}

	private String getCodeValid(String number) throws ValidateException {

		int digits = number.length();
		if (digits < 8 || digits > 18) {
			throw new ValidateException("Number out of range [8 | -- | 18]");
		}
		int[] token = { 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3 };
		int sum = 0;
		// a dezena mas próxima.
		int butNext;
		// digito verificador.
		int checkDigit;
		// pos setado conforme padrão.
		int pos = ((digits % 2) == 0) ? 0 : 1;
		// multiplica-se e soma-se os números do BarCode
		for (int i = 0; i < (digits - 1); i++) {
			sum += token[i + pos] * Character.getNumericValue(number.charAt(i));
		}
		// encontra-se a dezena mas próxima
		butNext = (sum - (sum % 10)) + 10;
		// usa-se após a subtração mod 10 para garantir só a unidade.
		checkDigit = (butNext - sum) % 10;
		if (checkDigit == Character.getNumericValue(number.charAt(digits - 1))) {
			return number;
		} else {
			throw new ValidateException("Number invalid barcode");
		}
	}

	public String getNumber() {
		return number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Barcode other = (Barcode) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return number;
	}

}
