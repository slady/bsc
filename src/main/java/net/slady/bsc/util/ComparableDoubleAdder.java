package net.slady.bsc.util;

import java.util.concurrent.atomic.DoubleAdder;

public class ComparableDoubleAdder extends DoubleAdder implements Comparable<ComparableDoubleAdder> {

	@Override
	public int compareTo(final ComparableDoubleAdder o) {
		return Double.compare(this.doubleValue(), o.doubleValue());
	}

}
