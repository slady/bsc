package net.slady.bsc.exceptions;

/**
 * Exception to inform the user about in incorrect Price from the input.
 */
public class IncorrectPriceException extends Exception {

	/**
	 * Creates the Exception to inform the user about in incorrect format from the input.
	 * @param input the incorrect input line
	 */
	public IncorrectPriceException(final String input) {
		super(input);
	}

}
