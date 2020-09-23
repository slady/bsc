package net.slady.bsc.service;

import net.slady.bsc.entities.PostalCodeWeightPair;
import net.slady.bsc.entities.WeightAndPricePair;
import net.slady.bsc.exceptions.IncorrectWeightException;
import net.slady.bsc.exceptions.InputFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderService {

	private final InputParserService inputParserService;

	private final PackageService packageService;

	public FileReaderService(final InputParserService inputParserService, final PackageService packageService) {
		this.inputParserService = inputParserService;
		this.packageService = packageService;
	}

	public void weightsImport(final String weightsFileName) throws IOException, IncorrectWeightException, InputFormatException {
		try (BufferedReader br = new BufferedReader(new FileReader(weightsFileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				final PostalCodeWeightPair postalCodeWeightPair = inputParserService.parsePostalCodeAndWeight(line);
				packageService.add(postalCodeWeightPair.getPostalCode(), postalCodeWeightPair.getWeight());
			}
		}
	}

	public void pricesImport(final String pricesFileName) throws IOException, InputFormatException, IncorrectWeightException {
		try (BufferedReader br = new BufferedReader(new FileReader(pricesFileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				final WeightAndPricePair weightAndPricePair = inputParserService.parseWeightAndPrice(line);
//				packageService.add(postalCodeWeightPair.getPostalCode(), postalCodeWeightPair.getWeight());
			}
		}
	}

}
