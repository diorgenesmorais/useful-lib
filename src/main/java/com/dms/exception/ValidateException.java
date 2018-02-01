package com.dms.exception;

/**
 * {@code ValidateException} of type checked.
 * 
 * @author Diorgenes Morais
 * @version 1.0.0
 */
public class ValidateException extends RuntimeException {

	static final long serialVersionUID = -998396896825227330L;

	public ValidateException() {
		super();
	}

	/**
	 * @param message
	 *            is a message to the layer higher.
	 */
	public ValidateException(String message) {
		super(message);
	}

	/**
	 * @param message
	 *            is a message to the layer higher.
	 * @param cause
	 *            a {@code Object} of type {@code Throwable} to the layer
	 *            higher.
	 */
	public ValidateException(String message, Throwable cause) {
		super(message, cause);
	}
}
