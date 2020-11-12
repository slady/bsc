package net.slady.bsc.service;

import net.slady.bsc.entities.PostalCodeWeightPair;
import net.slady.bsc.entities.WeightAndPricePair;
import net.slady.bsc.exceptions.IncorrectWeightException;
import net.slady.bsc.exceptions.InputFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Service for reading input files.
 */
public class FileReaderService {

	/**
	 * The InputParserService for reading values.
	 */
	private final InputParserService inputParserService;

	/**
	 * The PackageService for storing values.
	 */
	private final PackageService packageService;

	/**
	 * The constructor wit FileReaderService.
	 * @param inputParserService the InputParserService for reading values
	 * @param packageService the PackageService for storing values
	 */
	public FileReaderService(final InputParserService inputParserService, final PackageService packageService) {
		this.inputParserService = inputParserService;
		this.packageService = packageService;
	}

	/**
	 * Imports weight files.
	 * @param weightsFileName input file name of the file containing weights information
	 * @throws IOException if I/O goes wrong
	 * @throws IncorrectWeightException in the case of an incorrect weight
	 * @throws InputFormatException in the case of an incorrect input format
	 */
	public void weightsImport(final String weightsFileName) throws IOException, IncorrectWeightException, InputFormatException {
		try (BufferedReader br = new BufferedReader(new FileReader(weightsFileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				final PostalCodeWeightPair postalCodeWeightPair = inputParserService.parsePostalCodeAndWeight(line);
				packageService.addWeightToPostalCode(postalCodeWeightPair.getPostalCode(), postalCodeWeightPair.getWeight());
			}
		}
	}

	/**
	 * Imports prices files.
	 * @param pricesFileName input file name of the file containing price information
	 * @throws IOException in case I/O goes wrong
	 * @throws InputFormatException in the case of an incorrect input format
	 * @throws IncorrectWeightException in the case of an incorrect weight
	 */
	public void pricesImport(final String pricesFileName) throws IOException, InputFormatException, IncorrectWeightException {
		try (BufferedReader br = new BufferedReader(new FileReader(pricesFileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				packageService.addWeightAndPricePair(inputParserService.parseWeightAndPrice(line));
			}
		}
	}

}
