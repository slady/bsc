package net.slady.bsc.service;

import net.slady.bsc.entities.WeightAndPricePair;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Service for storing information about postal codes and package weights.
 * This service does not use a database storage as per customer request.
 * Instead of a DB table, this service stores values in a {@link Map} and {@link List}.
 */
public class PackageService {

	/**
	 * The map for storing Postal Codes as keys and Weight as their values.
	 * This map has to be thread safe.
	 */
	private final ConcurrentHashMap<String, Double> packageStorage = new ConcurrentHashMap<>();

	/**
	 * The List for storing Weight and Price values.
	 */
	private final List<WeightAndPricePair> priceStorage = new ArrayList<>();

	/**
	 * Adds weight to a specific postal code.
	 * @param postalCode the postal code to be added to
	 * @param weight weight to be added
	 */
	public void addWeightToPostalCode(final String postalCode, final double weight) {
		packageStorage.put(postalCode, packageStorage.computeIfAbsent(postalCode, k -> 0.0) + weight);
	}

	/**
	 * Gets a list of postal codes and their total weights.
	 * The list is ordered by the total weight values in the descending order.
	 * @return list of postal codes and their total weights in a {@link LinkedHashMap}
	 */
	public Map<String, Double> getWeightLines() {
		return packageStorage.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed())
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
				(oldValue, newValue) -> oldValue, LinkedHashMap::new));
	}

	/**
	 * Gets the Price for a given Weight.
	 * <p>The values are to be searched in an internal <code>priceStorage</code> field.
	 * <p>Meaning: the Price is a delivery fee of a package weighing more than or exactly the Weight.
	 * @param weight the Weight of the Package
	 * @return the Price of the Package for a given Weight
	 */
	public double getPriceForWeight(final double weight) {
		return priceStorage.stream().filter(p -> p.getWeight() <= weight)
			.max(Comparator.comparing(WeightAndPricePair::getWeight))
			.map(WeightAndPricePair::getPrice).orElse(.0);
	}

	public boolean isPrice() {
		return !priceStorage.isEmpty();
	}

	/**
	 * Adds a new value to the <code>priceStorage</code> field.
	 * This storage is not thread safe, so all adding must be done before the first call of the method <code>getPriceForWeight</code>!
	 * @param weightAndPrice the Weight and Price to be added
	 */
	public void addWeightAndPricePair(final WeightAndPricePair weightAndPrice) {
		priceStorage.add(weightAndPrice);
	}

}
