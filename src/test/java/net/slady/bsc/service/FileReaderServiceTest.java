package net.slady.bsc.service;

import net.slady.bsc.exceptions.IncorrectWeightException;
import net.slady.bsc.exceptions.InputFormatException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit tests of the {@link FileReaderService}.
 */
public class FileReaderServiceTest {

	/**
	 * Test file prefix.
	 */
	public static final String TEST_FILE_PREFIX = "src/test/resources/";

	/**
	 * Test file name.
	 */
	private static final String TEST_WEIGHTS_FILE_NAME = TEST_FILE_PREFIX + "weights.txt";

	/**
	 * Test file name.
	 */
	private static final String TEST_PRICES_FILE_NAME = TEST_FILE_PREFIX + "prices.txt";

	/**
	 * The service under test.
	 */
	private FileReaderService service;

	/**
	 * Prepares the services for testing.
	 */
	@Before
	public void prepareService() {
		final InputParserService inputParserService = new InputParserService();
		final PackageService packageService = new PackageService();
		service = new FileReaderService(inputParserService, packageService);
	}

	@Test
	public void weightsImportTest() throws InputFormatException, IncorrectWeightException, IOException {
		service.weightsImport(TEST_WEIGHTS_FILE_NAME);
	}

	@Test
	public void pricesImportTest() throws IOException, IncorrectWeightException, InputFormatException {
		service.pricesImport(TEST_PRICES_FILE_NAME);
	}

}
