package net.slady.bsc.exceptions;

/**
 * Exception to inform the user about in incorrect format from the input.
 */
public class InputFormatException extends Exception {

	/**
	 * Creates the Exception to inform the user about in incorrect format from the input.
	 * @param input the incorrect input line
	 */
	public InputFormatException(final String input) {
		super(input);
	}

}
