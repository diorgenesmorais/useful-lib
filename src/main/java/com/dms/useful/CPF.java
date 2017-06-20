package com.dms.useful;

import com.dms.annotations.ToDo;
import com.dms.exceptions.ValidateException;

/**
 * {@link Document} {@code CPF} can't be invalid throws
 * {@link ValidateException}, it must be number valid.
 * 
 * @author Diorgenes Morais
 * @version 1.0.1
 */
public final class CPF extends Document {

	/**
	 * Complete construct
	 * 
	 * @param number
	 *            number of this.
	 * @throws ValidateException
	 *             if is invalid.
	 */
	public CPF(String number) throws ValidateException {
		super(number);
	}

	@ToDo(assignedTo = "Diorgenes", item = "este método não válida os CPF 111.111.111-11, 222.222.222-22...")
	@Override
	protected String validateNumber(String number) throws ValidateException {

		int soma = 0, peso, qtd, dv, dezena, unidade;

		if (!number.matches("\\d{11}")) {
			throw new ValidateException("O CPF deve possuir 11 digitos.");
		}

		qtd = number.length();

		dv = 10;
		for (int i = 0; i < 9; i++) {
			soma += Character.getNumericValue(number.charAt(i)) * dv;
			dv--;
		}

		peso = 11 - (soma % 11);
		dezena = (peso >= 10) ? 0 : peso;

		soma = 0;

		dv = 11;
		for (int i = 0; i < 9; i++) {
			soma += Character.getNumericValue(number.charAt(i)) * dv;
			dv--;
		}

		peso = 11 - ((soma + (dezena * 2)) % 11);
		unidade = (peso >= 10) ? 0 : peso;

		if ((dezena == Character.getNumericValue(number.charAt(qtd - 2)))
				& (unidade == Character.getNumericValue(number.charAt(qtd - 1)))) {
			return number;
		} else {
			throw new ValidateException("Não é um número de CPF válido");
		}
	}

	@Override
	public String getNumberFormatted() {
		return getNumber().replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
	}

}
