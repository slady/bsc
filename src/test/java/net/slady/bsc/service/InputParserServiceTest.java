package net.slady.bsc.service;

import net.slady.bsc.TestHelper;
import net.slady.bsc.entities.PostalCodeWeightPair;
import net.slady.bsc.entities.WeightAndPricePair;
import net.slady.bsc.exceptions.IncorrectWeightException;
import net.slady.bsc.exceptions.InputFormatException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests of the {@link InputParserService}.
 */
public class InputParserServiceTest {

	/**
	 * The service under test.
	 */
	private InputParserService service;

	/**
	 * Creates the service for testing.
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
		TestHelper.assertDoubleEquals(3.2, postalCodeWeightPair.getWeight());
		Assert.assertEquals("09300", postalCodeWeightPair.getPostalCode());
	}

	/**
	 * Test the correct parsing of Weight in the format ".1" without the leading digit.
	 * @throws InputFormatException if something goes wrong
	 * @throws IncorrectWeightException if something goes wrong
	 */
	@Test
	public void parsePostalCodeAndWeightWithoutLeadingDigitTest() throws InputFormatException, IncorrectWeightException {
		service.parsePostalCodeAndWeight(".1 12345");
	}

	/**
	 * Test an incorrect number of Postal Code digits on the input of Postal Code and Weight.
	 * the {@link InputFormatException} is expected to be thrown.
	 * @throws InputFormatException is expected to be thrown.
	 * @throws IncorrectWeightException if something goes wrong
	 */
	@Test(expected = InputFormatException.class)
	public void parseParsePostalCodeAndWeightIncorrectInputFormatTest() throws IncorrectWeightException, InputFormatException {
		service.parsePostalCodeAndWeight("1.2 123");
	}

	/**
	 * Test an incorrect Weight on the input of the Postal Code and Weight.
	 * the {@link IncorrectWeightException} is expected to be thrown.
	 * @throws IncorrectWeightException if something goes wrong
	 * @throws InputFormatException this exception is the expected result
	 */
	@Test(expected = IncorrectWeightException.class)
	public void parseParsePostalCodeAndWeightIncorrectWeightTest() throws IncorrectWeightException, InputFormatException {
		service.parsePostalCodeAndWeight("0.0 00000");
	}

	/**
	 * Test a complete nonsense on the input of the Postal Code and Weight.
	 * the {@link InputFormatException} is expected to be thrown.
	 * @throws InputFormatException this exception is the expected result
	 * @throws IncorrectWeightException  if something goes wrong
	 */
	@Test(expected = InputFormatException.class)
	public void parsePostalCodeAndWeightWrongTest() throws InputFormatException, IncorrectWeightException {
		service.parsePostalCodeAndWeight("error");
	}

	/**
	 * Test to insert all the input values of Weights and Prices from the homework text.
	 * @throws InputFormatException if something goes wrong
	 * @throws IncorrectWeightException if something goes wrong
	 */
	@Test
	public void parseWeightAndPriceRegexTest() throws InputFormatException, IncorrectWeightException {
		service.parseWeightAndPrice("10 5.00");
		service.parseWeightAndPrice("5 2.50");
		service.parseWeightAndPrice("3 2.00");
		service.parseWeightAndPrice("2 1.50");
		service.parseWeightAndPrice("1 1.00");
		service.parseWeightAndPrice("0.5 0.70");
		service.parseWeightAndPrice("0.2 0.50");
	}

	/**
	 * Test if parsing of the Weight and Price values works correctly.
	 * @throws InputFormatException if something goes wrong
	 * @throws IncorrectWeightException if something goes wrong
	 */
	@Test
	public void parseWeightAndPriceResultTest() throws InputFormatException, IncorrectWeightException {
		final WeightAndPricePair weightAndPricePair = service.parseWeightAndPrice("0.5 0.70");

		Assert.assertNotNull(weightAndPricePair);
		TestHelper.assertDoubleEquals(.5, weightAndPricePair.getWeight());
		TestHelper.assertDoubleEquals(.7, weightAndPricePair.getPrice());
	}

	/**
	 * Test the correct parsing of Weight in the format ".1" without the leading digit.
	 * @throws InputFormatException if something goes wrong
	 * @throws IncorrectWeightException if something goes wrong
	 */
	@Test
	public void parseWeightAndPriceWithoutLeadingDigitTest() throws InputFormatException, IncorrectWeightException {
		service.parseWeightAndPrice(".1 .50");
	}

	/**
	 * Test an incorrect number of Price digits on the input of Weight and Price.
	 * the {@link InputFormatException} is expected to be thrown.
	 * @throws InputFormatException is expected to be thrown.
	 * @throws IncorrectWeightException if something goes wrong
	 */
	@Test(expected = InputFormatException.class)
	public void parseWeightAndPriceIncorrectInputFormatTest() throws IncorrectWeightException, InputFormatException {
		service.parseWeightAndPrice("1.2 1.2");
	}

	/**
	 * Test an incorrect Weight on the input of the Weight and Price.
	 * the {@link IncorrectWeightException} is expected to be thrown.
	 * @throws IncorrectWeightException if something goes wrong
	 * @throws InputFormatException this exception is the expected result
	 */
	@Test(expected = IncorrectWeightException.class)
	public void parsePostalCodeAndWeightIncorrectWeightTest() throws IncorrectWeightException, InputFormatException {
		service.parseWeightAndPrice("0.0 0.00");
	}

	/**
	 * Test a complete nonsense on the input of the Weight and Price.
	 * the {@link InputFormatException} is expected to be thrown.
	 * @throws InputFormatException this exception is the expected result
	 * @throws IncorrectWeightException  if something goes wrong
	 */
	@Test(expected = InputFormatException.class)
	public void parseWeightAndPriceWrongTest() throws InputFormatException, IncorrectWeightException {
		service.parseWeightAndPrice("error");
	}

}
