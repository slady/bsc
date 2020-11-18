package net.slady.bsc.service;

import net.slady.bsc.TestHelper;
import net.slady.bsc.entities.WeightAndPricePair;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

/**
 * Unit test of the {@link PackageService}.
 */
public class PackageServiceTest {

	/**
	 * Test adds various Weights to Postal Codes and reads them in the correct order.
	 */
	@Test
	public void addWeightToPostalCodeTest() {
		final PackageService service = new PackageService();
		service.addWeightToPostalCode("12345", 1.1);
		service.addWeightToPostalCode("12345", 2.2);

		final Map<String, Double> lines1 = service.getWeightLines();
		Assert.assertNotNull(lines1);
		Assert.assertEquals(1, lines1.size());
		TestHelper.assertDoubleEquals(3.3, lines1.get("12345"));

		service.addWeightToPostalCode("54321", 5.5);
		final Map<String, Double> lines2 = service.getWeightLines();
		Assert.assertNotNull(lines2);
		Assert.assertEquals(2, lines2.size());
		TestHelper.assertDoubleEquals(3.3, lines2.get("12345"));
		TestHelper.assertDoubleEquals(5.5, lines2.get("54321"));

		service.addWeightToPostalCode("98765", 4.4);
		final Map<String, Double> lines3 = service.getWeightLines();
		final Iterator<Map.Entry<String, Double>> iterator = lines3.entrySet().iterator();
		TestHelper.assertDoubleEquals(5.5, iterator.next().getValue());
		TestHelper.assertDoubleEquals(4.4, iterator.next().getValue());
		TestHelper.assertDoubleEquals(3.3, iterator.next().getValue());
		Assert.assertFalse(iterator.hasNext());
	}

	/**
	 * Test adds various Prices to Weights and reads the correct values.
	 */
	@Test
	public void addWeightAndPricePairTest() {
		final PackageService service = new PackageService();
		service.addWeightAndPricePair(new WeightAndPricePair(10, 5));
		service.addWeightAndPricePair(new WeightAndPricePair(5, 2.5));
		service.addWeightAndPricePair(new WeightAndPricePair(3, 2));
		service.addWeightAndPricePair(new WeightAndPricePair(2, 1.5));
		service.addWeightAndPricePair(new WeightAndPricePair(1, 1));
		service.addWeightAndPricePair(new WeightAndPricePair(.5, .7));
		service.addWeightAndPricePair(new WeightAndPricePair(.2, .5));

		TestHelper.assertDoubleEquals(5, service.getPriceForWeight(11));
		TestHelper.assertDoubleEquals(5, service.getPriceForWeight(10));
		TestHelper.assertDoubleEquals(2.5, service.getPriceForWeight(9));
		TestHelper.assertDoubleEquals(2.5, service.getPriceForWeight(5));
	}

}
