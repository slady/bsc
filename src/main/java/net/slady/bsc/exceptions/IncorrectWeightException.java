package net.slady.bsc.exceptions;

/**
 * Exception to inform the user about in incorrect Weight from the input.
 */
public class IncorrectWeightException extends Exception {

	/**
	 * Creates the Exception to inform the user about in incorrect Weight from the input.
	 * @param input the incorrect input line
	 */
	public IncorrectWeightException(final String input) {
		super(input);
	}

}
