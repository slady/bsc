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
				break;
			}

			final PostalCodeWeightPair postalCodeWeightPair;

			try {
				// parse the input text
				postalCodeWeightPair = inputParserService.parsePostalCodeAndWeight(input);
			} catch (final InputFormatException e) {
				// handle InputFormatException
				System.err.println("Error: Incorrect input format!");
				System.err.println("Input line format:");
				System.err.println("<weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator><space><postal code: fixed 5 digits>");
				continue;
			} catch (final IncorrectWeightException e) {
				// handle IncorrectWeightException
				System.err.println("Error: Incorrect input weight!");
				System.err.println("Weight must be a positive number!");
				continue;
			}

			packageService.add(postalCodeWeightPair.getPostalCode(), postalCodeWeightPair.getWeight());
		}
	}

}
