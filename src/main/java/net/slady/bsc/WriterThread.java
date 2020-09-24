package net.slady.bsc;

import net.slady.bsc.service.PackageService;
import net.slady.bsc.util.ComparableDoubleAdder;

import java.util.LinkedHashMap;
import java.util.Locale;

/**
 * The WriterThread class writes output to console once per minute.
 * Each line consists of postal code and total weight of all packages for that postal code.
 * If the fees related to package weight were specified to
 * process as the command line argument specified at program run,
 * the thread also writes the prices to the output.
 */
public class WriterThread extends Thread {

	/**
	 * The timeout for the thread is once per minute.
	 */
	public static final int TIMEOUT = 60_000;

	/**
	 * The indicator if this tread is still running.
	 */
	private volatile boolean running = true;

	/**
	 * The PackageService for reading the values.
	 */
	private final PackageService packageService;

	/**
	 * The constructor wit PackageService.
	 * @param packageService the PackageService for reading values
	 */
	public WriterThread(final PackageService packageService) {
		this.packageService = packageService;
	}

	/**
	 * The run() method writes output to console once per minute.
	 * Each line consists of postal code and total weight of all packages for that postal code.
	 * If the fees related to package weight were specified to
	 * process as the command line argument specified at program run,
	 * the thread also writes the prices to the output.
	 */
	@Override
	public void run() {
		while (running) {
			try {
				sleep(TIMEOUT);
				writeOutput();
			} catch (final InterruptedException e) {
				break;
			}
		}
	}

	/**
	 * Writ lines to the output.
	 */
	private void writeOutput() {
		final LinkedHashMap<String, ComparableDoubleAdder> weightLines = packageService.getWeightLines();

		if (packageService.isPrice()) {
			for (String postalCode : weightLines.keySet()) {
				final double weight = weightLines.get(postalCode).doubleValue();
				System.out.format(Locale.US, "%s %1.3f %1.2f\n", postalCode, weight, packageService.getPriceForWeight(weight));
			}
		} else {
			for (String postalCode : weightLines.keySet()) {
				System.out.format(Locale.US, "%s %1.3f\n", postalCode, weightLines.get(postalCode).doubleValue());
			}
		}
	}

	/**
	 * The method to request this Thread to stop operation.
	 */
	public void stopRequest() {
		running = false;
		this.interrupt();
	}

}
