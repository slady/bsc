package net.slady.bsc.service;

import net.slady.bsc.entities.PostalCodeWeightPair;
import net.slady.bsc.entities.WeightAndPricePair;
import net.slady.bsc.exceptions.InputFormatException;
import net.slady.bsc.exceptions.IncorrectWeightException;

import java.util.regex.Pattern;

/**
 * Service for parsing user input or files in a text form.
 */
public class InputParserService {

	/**
	 * The user input validation regular expression for Postal Code and Weight.
	 */
	private static final String INPUT_PATTERN_REGEX_POSTAL_CODE_AND_WEIGHT = "\\d*(\\.\\d{1,3})?\\s\\d{5}";

	/**
	 * The input validation regular expression for Weight and Price.
	 */
	private static final String INPUT_PATTER_REGEX_WEIGHT_AND_PRICE = "\\d*(\\.\\d{1,3})?\\s\\d*\\.\\d{2}";

	/**
	 * Method for parsing the user input or the input from a file of Postal Code and Weight.
	 *
	 * <br>The format is as follows:
	 * <pre>&lt;weight: positive number, &gt;0, maximal 3 decimal places, . (dot) as decimal separator&gt;&lt;space&gt;&lt;postal code: fixed 5 digits&gt;</pre>
	 * @param input the input line from the user or a file
	 * @return an instance of the {@link PostalCodeWeightPair} class containing the information parsed from the input string
	 * @throws InputFormatException in the case of an incorrect input format
	 * @throws IncorrectWeightException in the case of an incorrect weight
	 */
	public PostalCodeWeightPair parsePostalCodeAndWeight(final String input) throws InputFormatException, IncorrectWeightException {
		// check whether the input string conforms to the required format
		if (!Pattern.matches(INPUT_PATTERN_REGEX_POSTAL_CODE_AND_WEIGHT, input)) {
			throw new InputFormatException(input);
		}

		// split the input string using a space character
		final String[] splitInput = input.split(" ");

		// parse the weight from the input
		final double weight = Double.parseDouble(splitInput[0]);

		// check whether the weight is positive
		if (weight <= 0) {
			throw new IncorrectWeightException(input);
		}

		// create and return the result object
		return new PostalCodeWeightPair(splitInput[1], weight);
	}

	/**
	 * Method for parsing
	 * @param input the input line from a file
	 * @return an instance of the {@link WeightAndPricePair} class containing the information parsed from the input string
	 * @throws InputFormatException in the case of an incorrect input format
	 */
	public WeightAndPricePair parseWeightAndPrice(final String input) throws InputFormatException, IncorrectWeightException {
		// check whether the input string conforms to the required format
		if (!Pattern.matches(INPUT_PATTER_REGEX_WEIGHT_AND_PRICE, input)) {
			throw new InputFormatException(input);
		}

		// split the input string using a space character
		final String[] splitInput = input.split(" ");

		// parse the weight from the input
		final double weight = Double.parseDouble(splitInput[0]);

		// check whether the weight is positive
		if (weight <= 0) {
			throw new IncorrectWeightException(input);
		}

		// parse the price from the input
		final double price = Double.parseDouble(splitInput[1]);

		return new WeightAndPricePair(weight, price);
	}

}
