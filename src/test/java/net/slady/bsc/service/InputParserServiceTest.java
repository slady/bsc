package net.slady.bsc.service;

import net.slady.bsc.entities.PostalCodeWeightPair;
import net.slady.bsc.exceptions.IncorrectWeightException;
import net.slady.bsc.exceptions.InputFormatException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static net.slady.bsc.TestProperties.DELTA;

/**
 * Unit tests of the {@link InputParserService}.
 */
public class InputParserServiceTest {

	/**
	 * The service under test.
	 */
	private InputParserService service;

	/**
	 * Create the service for testing.
	 */
	@Before
	public void prepareService() {
		service = new InputParserService();
	}

	/**
	 * Test to insert all the input values of Postal Codes and Weights from the homework text.
	 * @throws InputFormatException if something goes wrong
	 * @throws IncorrectWeightException if something goes wrong
	 */
	@Test
	public void parsePostalCodeAndWeightRegexTest() throws InputFormatException, IncorrectWeightException {
		service.parsePostalCodeAndWeight("3.4 08801");
		service.parsePostalCodeAndWeight("2 90005");
		service.parsePostalCodeAndWeight("12.56 08801");
		service.parsePostalCodeAndWeight("5.5 08079");
		service.parsePostalCodeAndWeight("3.2 09300");
	}

	/**
	 * Test if parsing of the Postal Code and Weight values works correctly.
	 * @throws InputFormatException if something goes wrong
	 * @throws IncorrectWeightException if something goes wrong
	 */
	@Test
	public void parsePostalCodeAndWeightResultTest() throws InputFormatException, IncorrectWeightException {
		final PostalCodeWeightPair postalCodeWeightPair = service.parsePostalCodeAndWeight("3.2 09300");

		Assert.assertNotNull(postalCodeWeightPair);
		Assert.assertEquals(3.2, postalCodeWeightPair.getWeight(), DELTA);
		Assert.assertEquals("09300", postalCodeWeightPair.getPostalCode());
	}

	/**
	 * Test an incorrect number of Postal Code digits on the input of Postal Code and Weight,
	 * the {@link InputFormatException} is expected to be thrown.
	 * @throws InputFormatException is expected to be thrown.
	 * @throws IncorrectWeightException if something goes wrong
	 */
	@Test(expected = InputFormatException.class)
	public void parseParsePostalCodeAndWeightIncorrectInputFormatTest() throws IncorrectWeightException, InputFormatException {
		service.parsePostalCodeAndWeight("1.2 123");
	}

	/**
	 * Test an incorrect Weight on the input of the Postal Code and Weight,
	 * the {@link IncorrectWeightException} is expected to be thrown.
	 * @throws IncorrectWeightException if something goes wrong
	 * @throws InputFormatException this exception is the expected result
	 */
	@Test(expected = IncorrectWeightException.class)
	public void parseParsePostalCodeAndWeightIncorrectWeightTest() throws IncorrectWeightException, InputFormatException {
		service.parsePostalCodeAndWeight("0.0 00000");
	}

	/**
	 * Test a complete nonsense on the input of the Postal Code and Weight,
	 * the {@link InputFormatException} is expected to be thrown.
	 * @throws InputFormatException this exception is the expected result
	 * @throws IncorrectWeightException  if something goes wrong
	 */
	@Test(expected = InputFormatException.class)
	public void parsePostalCodeAndWeightWrongTest() throws InputFormatException, IncorrectWeightException {
		service.parsePostalCodeAndWeight("error");
	}

}
