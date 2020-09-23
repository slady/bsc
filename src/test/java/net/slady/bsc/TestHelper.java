package net.slady.bsc;

import org.junit.Assert;

/**
 * Helper class for other unit tests.
 */
public class TestHelper {

	/**
	 * The Delta for comparing double values.
	 */
	private static final double DELTA = 0.0001;

	/**
	 * The helper for calling <code>Assert.assertEquals()</code> with double values.
	 * @param expected the expected value
	 * @param actual the actual value
	 */
	public static void assertDoubleEquals(final double expected, final double actual) {
		Assert.assertEquals(expected, actual, DELTA);
	}

}
