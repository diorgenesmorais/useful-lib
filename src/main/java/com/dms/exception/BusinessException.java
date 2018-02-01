package com.dms.exception;

/**
 * {@code BusinessException} it is a class of exceptions that can be generated
 * by business rules.
 * 
 * @author Diorgenes Morais
 * @version 1.0.0
 */
public class BusinessException extends RuntimeException {

	static final long serialVersionUID = 3980267702545494075L;

	public BusinessException() {
		super();
	}

	/**
	 * @param message
	 *            is a message to the layer higher.
	 */
	public BusinessException(String message) {
		super(message);
	}

	/**
	 * @param message
	 *            is a message to the layer higher.
	 * @param cause
	 *            a {@code Object} of type {@code Throwable} to the layer
	 *            higher.
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	@Override
	public synchronized Throwable getCause() {
		return super.getCause();
	}

	/**
	 * Try to get a {@code BusinessException} of stack.
	 * 
	 * @param exception
	 *            Package with exceptions of a {@code Throwable}.
	 * @return {@code BusinessException} or {@code null} if not found.
	 */
	public static BusinessException getBusinessException(Throwable exception) {
		if (exception instanceof BusinessException) {
			return (BusinessException) exception;
		} else if (exception.getCause() != null) {
			return getBusinessException(exception.getCause());
		}
		return null;
	}
}
