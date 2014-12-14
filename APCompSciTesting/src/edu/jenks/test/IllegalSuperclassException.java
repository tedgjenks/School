/**
 * 
 */
package edu.jenks.test;

/**
 * @author Ted
 *
 */
public class IllegalSuperclassException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7014579922200237654L;

	/**
	 * 
	 */
	public IllegalSuperclassException() {
	}

	/**
	 * @param arg0
	 */
	public IllegalSuperclassException(String arg0) {
		super(arg0);
	}

	/**
	 * @param cause
	 */
	public IllegalSuperclassException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public IllegalSuperclassException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public IllegalSuperclassException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
