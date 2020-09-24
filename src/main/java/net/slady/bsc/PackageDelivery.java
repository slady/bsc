package net.slady.bsc;

import net.slady.bsc.entities.PostalCodeWeightPair;
import net.slady.bsc.exceptions.IncorrectWeightException;
import net.slady.bsc.exceptions.InputFormatException;
import net.slady.bsc.service.FileReaderService;
import net.slady.bsc.service.InputParserService;
import net.slady.bsc.service.PackageService;

import java.io.IOException;
import java.util.Scanner;

/**
 * This is the main starting class for the Package Delivery software.
 * This piece of software was requested by Banking Software Company s.r.o.
 * as a homework for a job interview process.
 */
public class PackageDelivery implements ErrorMessages {

	/**
	 * The "quit" keyword to exit the program loop.
	 */
	public static final String KEYWORD_QUIT = "quit";

	/**
	 * The entry point of the Package Delivery program.
	 * @param args command line arguments as described in the README file
	 */
	public static void main(final String... args) {
		// initialize InputParserService
		final InputParserService inputParserService = new InputParserService();
		// initialize PackageService
		final PackageService packageService = new PackageService();

		// handle command line arguments
		handleCommandLineArgumetns(inputParserService, packageService, args);

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
				continue;
			} catch (final IncorrectWeightException e) {
				// handle IncorrectWeightException
				System.err.println(MESSAGE_ERROR_INCORRECT_INPUT_WEIGHT);
				continue;
			}

			// add the values read from the user to the packageService
			packageService.addWeightToPostalCode(postalCodeWeightPair.getPostalCode(), postalCodeWeightPair.getWeight());
		}
	}

	/**
	 * This methods reads and hangles the command line arguments.
	 * @param inputParserService
	 * @param packageService
	 * @param args the command line arguments
	 */
	private static void handleCommandLineArgumetns(final InputParserService inputParserService, final PackageService packageService, final String[] args) {
		// handle command line arguments
		if (args.length > 0) {
			// check for wrong number of parameters
			if (args.length > 2) {
				System.err.println(MESSAGE_WRONG_NUMBER_OF_PARAMETERS);
				System.exit(ERROR_CODE_WRONG_NUMBER_OF_PARAMETERS);
			}

			// initialize FileReaderService
			final FileReaderService fileReaderService = new FileReaderService(inputParserService, packageService);

			// Weights file name as argument number #0
			final String weightsFileName = args[0];

			try {
				// read the Weights input file
				fileReaderService.weightsImport(weightsFileName);
			} catch (final IOException e) {
				// handle IOException
				System.err.println(MESSAGE_ERROR_CANNOT_READ_FROM_FILE);
				System.err.println(MESSAGE_FILE_NAME + weightsFileName);
				System.exit(ERROR_CODE_CANNOT_OPEN_FILE);
			} catch (final InputFormatException e) {
				// handle InputFormatException
				System.err.println(MESSAGE_ERROR_INCORRECT_INPUT_FORMAT);
				System.err.println(MESSAGE_FILE_NAME + weightsFileName);
				System.err.println(MESSAGE_PROBLEM_IN_LINE + e.getMessage());
				System.exit(ERROR_CODE_FILE_CONTENTS_INCORRECT);
			} catch (final IncorrectWeightException e) {
				// handle IncorrectWeightException
				System.err.println(MESSAGE_ERROR_INCORRECT_INPUT_WEIGHT);
				System.err.println(MESSAGE_FILE_NAME + weightsFileName);
				System.err.println(MESSAGE_PROBLEM_IN_LINE + e.getMessage());
				System.exit(ERROR_CODE_FILE_CONTENTS_INCORRECT);
			}

			if (args.length > 1) {
				// Prices file name as argument number #1
				final String pricesFileName = args[1];

				try {
					// read the Prices input file
					fileReaderService.pricesImport(pricesFileName);
				} catch (final IOException e) {
					// handle IOException
					System.err.println(MESSAGE_ERROR_CANNOT_READ_FROM_FILE);
					System.err.println(MESSAGE_FILE_NAME + pricesFileName);
					System.exit(ERROR_CODE_CANNOT_OPEN_FILE);
				} catch (final InputFormatException e) {
					// handle InputFormatException
					System.err.println(MESSAGE_ERROR_INCORRECT_INPUT_PRICE);
					System.err.println(MESSAGE_FILE_NAME + pricesFileName);
					System.err.println(MESSAGE_PROBLEM_IN_LINE + e.getMessage());
					System.exit(ERROR_CODE_FILE_CONTENTS_INCORRECT);
				} catch (final IncorrectWeightException e) {
					// handle IncorrectWeightException
					System.err.println(MESSAGE_ERROR_INCORRECT_INPUT_WEIGHT);
					System.err.println(MESSAGE_FILE_NAME + pricesFileName);
					System.err.println(MESSAGE_PROBLEM_IN_LINE + e.getMessage());
					System.exit(ERROR_CODE_FILE_CONTENTS_INCORRECT);
				}
			}
		}
	}

}
