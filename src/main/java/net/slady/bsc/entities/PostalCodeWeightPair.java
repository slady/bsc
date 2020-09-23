package net.slady.bsc.entities;

/**
 * The wrapper class containing a Postal Code and Weight.
 * This class is <b>immutable</b>.
 */
public class PostalCodeWeightPair {

	/**
	 * The Postal Code.
	 */
	final String postalCode;

	/**
	 * The Weight.
	 */
	final double weight;

	/**
	 * The constructor for wrapper {@link PostalCodeWeightPair} with a Postal Code and Weight
	 * @param postalCode the Postal Code
	 * @param weight the Weight
	 */
	public PostalCodeWeightPair(final String postalCode, final double weight) {
		this.postalCode = postalCode;
		this.weight = weight;
	}

	/**
	 * Returns the Postal Code.
	 * @return the Postal Code
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Returns the Weight.
	 * @return the Weight
	 */
	public double getWeight() {
		return weight;
	}

}
