package net.slady.bsc.entities;

/**
 * The wrapper class for a Weight and Price.
 * This class is <b>immutable</b>.
 */
public class WeightAndPricePair {

	/**
	 * The Weight.
	 */
	final double weight;

	/**
	 * The Price.
	 */
	final double price;

	/**
	 * The constructor for wrapper {@link WeightAndPricePair} with a Weight and Price.
	 * @param weight the Weight
	 * @param price the Price
	 */
	public WeightAndPricePair(final double weight, final double price) {
		this.weight = weight;
		this.price = price;
	}

	/**
	 * Returns the Weight.
	 * @return the Weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Returns the Price.
	 * @return the Price
	 */
	public double getPrice() {
		return price;
	}

}
