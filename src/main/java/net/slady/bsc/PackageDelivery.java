package net.slady.bsc;

import net.slady.bsc.entities.PostalCodeWeightPair;
import net.slady.bsc.exceptions.IncorrectWeightException;
import net.slady.bsc.exceptions.InputFormatException;
import net.slady.bsc.service.FileReaderService;
import net.slady.bsc.service.InputParserService;
import net.slady.bsc.service.PackageService;

import java.util.Scanner;

/**
 * This is the main starting class for the Package Delivery software.
 * This piece of software was requested by Banking Software Company s.r.o.
 * as a homework for a job interview process.
 */
public class PackageDelivery {

	/**
	 * The "quit" keyword to exit the program loop.
	 */
	public static final String KEYWORD_QUIT = "quit";

	/** Error message. */
	public static final String MESSAGE_ERROR_INCORRECT_INPUT_FORMAT = "Error: Incorrect input format!";

	/** Error message. */
	public static final String MESSAGE_INPUT_LINE_FORMAT = "Input line format:";

	/** Error message. */
	public static final String MESSAGE_INPUT_LINE_FORMAT_DETAIL = "<weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator><space><postal code: fixed 5 digits>";

	/** Error message. */
	public static final String MESSAGE_ERROR_INCORRECT_INPUT_WEIGHT = "Error: Incorrect input weight!";

	/** Error message. */
	public static final String MESSAGE_WEIGHT_MUST_BE_A_POSITIVE_NUMBER = "Weight must be a positive number!";

	/**
	 * The entry point of the Package Delivery program.
	 * @param args command line arguments as described in the README file
	 */
	public static void main(final String... args) {
		final InputParserService inputParserService = new InputParserService();
		final PackageService packageService = new PackageService();

		if (args.length > 0) {
			new FileReaderService(inputParserService, packageService);
		}

		// create and start the WriterThread
		final WriterThread writerThread = new WriterThread(packageService);
		writerThread.start();

		// create a Scanner using the InputStream
		final Scanner scanner = new Scanner(System.in);

		// infinite loop
		while (true) {
			// use the Scanner to read a line of text from the user
			final String input = scanner.nextLine();
			final String trim = input.trim();

			// ignore empty lines
			if (trim.length() == 0) {
				continue;
			}

			// exit the infinite loop on the "quit" keyword
			if (KEYWORD_QUIT.equalsIgnoreCase(trim.toLowerCase())) {
				writerThread.stopRequest();
				break;
			}

			final PostalCodeWeightPair postalCodeWeightPair;

			try {
				// parse the input text
				postalCodeWeightPair = inputParserService.parsePostalCodeAndWeight(input);
			} catch (final InputFormatException e) {
				// handle InputFormatException
				System.err.println(MESSAGE_ERROR_INCORRECT_INPUT_FORMAT);
				System.err.println(MESSAGE_INPUT_LINE_FORMAT);
				System.err.println(MESSAGE_INPUT_LINE_FORMAT_DETAIL);
				continue;
			} catch (final IncorrectWeightException e) {
				// handle IncorrectWeightException
				System.err.println(MESSAGE_ERROR_INCORRECT_INPUT_WEIGHT);
				System.err.println(MESSAGE_WEIGHT_MUST_BE_A_POSITIVE_NUMBER);
				continue;
			}

			packageService.addWeightToPostalCode(postalCodeWeightPair.getPostalCode(), postalCodeWeightPair.getWeight());
		}
	}

}
