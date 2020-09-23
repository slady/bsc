package net.slady.bsc.entities;

public class PostalCodeWeightPair {

	final String postalCode;

	final double weight;

	public PostalCodeWeightPair(final String postalCode, final double weight) {
		this.postalCode = postalCode;
		this.weight = weight;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public double getWeight() {
		return weight;
	}

}
