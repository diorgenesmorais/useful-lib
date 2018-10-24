package com.dms.useful;

import com.dms.exception.ValidateException;

/**
 * {@code Document} class is a abstraction of (RG, CPF, CNPJ...) Note:
 * Internally using the {@code String} class to be immutable.
 * 
 * @author Diorgenes Morais
 * @version 1.0.4
 */
public abstract class Document {

	private final String number;

	public Document(String number) throws ValidateException {
		if (number == null) {
			throw new ValidateException("Parâmetro não pode ser nulo");
		}
		this.number = validateNumber(number.replaceAll("\\.|-|/", ""));
	}

	protected abstract String validateNumber(String number) throws ValidateException;

	/**
	 * Get document number
	 * 
	 * @return document number.
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Get number formatted with mask.
	 * 
	 * @return number formatted in a mask.
	 */
	public abstract String getNumberFormatted();

	@Override
	public String toString() {
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
		Document other = (Document) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

}
