package net.slady.bsc;

/**
 * This interface only contains the Error Codes and
 * the Error Messages for exception handling
 * to inform the user about error states that have occurred.
 */
public interface ErrorMessages {

	int ERROR_CODE_WRONG_NUMBER_OF_PARAMETERS = 1;

	int ERROR_CODE_CANNOT_OPEN_FILE = 2;

	int ERROR_CODE_FILE_CONTENTS_INCORRECT = 3;

	String MESSAGE_WRONG_NUMBER_OF_PARAMETERS = "Error: wrong number of input parameters!\n" +
		"Correct format is:\n" +
		"<program> [<weights_file> [<fees_file>]]";

	String MESSAGE_ERROR_INCORRECT_INPUT_FORMAT = "Error: Incorrect input format!\n" +
		"Input line format:" +
		"\n<weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator><space><postal code: fixed 5 digits>";

	String MESSAGE_ERROR_INCORRECT_INPUT_WEIGHT = "Error: Incorrect input weight!\n" +
		"Weight must be a positive number!";

	String MESSAGE_ERROR_INCORRECT_INPUT_PRICE = "Error: Incorrect input format!\n" +
		"Input line format:" +
		"\n<weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator><space><fee: positive number, >=0, fixed two decimals, . (dot) as decimal separator>";

	String MESSAGE_ERROR_CANNOT_READ_FROM_FILE = "Error: Cannot read from file!";

	String MESSAGE_FILE_NAME = "File name: ";

	String MESSAGE_PROBLEM_IN_LINE = "Problem in line: ";

}
