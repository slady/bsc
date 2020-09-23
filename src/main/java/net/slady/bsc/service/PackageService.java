package net.slady.bsc.service;

import net.slady.bsc.util.ComparableDoubleAdder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Service for storing information about postal codes and package weights.
 * This service does not use a database storage as per customer request.
 * Instead of a DB table, this service stores values in a Map.
 */
public class PackageService {

	/**
	 * The map for storing Postal Codes as keys and Weight as their values.
	 */
	private final ConcurrentHashMap<String, ComparableDoubleAdder> storage = new ConcurrentHashMap<>();

	/**
	 * Adds weight to a specific postal code.
	 * @param postalCode the postal code to be added to
	 * @param weight weight to be added
	 */
	public void add(final String postalCode, final double weight) {
		storage.computeIfAbsent(postalCode, k -> new ComparableDoubleAdder()).add(weight);
	}

	/**
	 * Gets a list of postal codes and their total weights.
	 * The list is ordered by the total weight values in the descending order.
	 * @return list of postal codes and their total weights in a {@link LinkedHashMap}
	 */
	public LinkedHashMap<String, ComparableDoubleAdder> getLines() {
		return storage.entrySet().stream().sorted(Map.Entry.comparingByValue())
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
				(oldValue, newValue) -> oldValue, LinkedHashMap::new));
	}

}
