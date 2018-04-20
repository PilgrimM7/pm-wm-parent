/**
 * 
 */
package com.pilgrimm.core.validate;

/**
 * @author liwei
 */
public class ValidateException extends Exception {

	private static final long serialVersionUID = 4650460830310244066L;

	/**
	 * 
	 */
	public ValidateException() {
	}

	/**
	 * @param message
	 */
	public ValidateException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ValidateException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ValidateException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ValidateException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
