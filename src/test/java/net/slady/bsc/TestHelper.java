package net.slady.bsc;

import org.junit.Assert;

/**
 * Helper class for other unit tests.
 */
public class TestHelper {

	private static final double DELTA = 0.0001;

	public static void assertDoubleEquals(final double expected, final double actual) {
		Assert.assertEquals(expected, actual, DELTA);
	}

}
