package net.slady.bsc.service;

import net.slady.bsc.util.ComparableDoubleAdder;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static net.slady.bsc.TestProperties.DELTA;

public class PackageServiceTest {

	@Test
	public void addTest() {
		final PackageService service = new PackageService();
		service.add("12345", 1.1);
		service.add("12345", 2.2);

		final LinkedHashMap<String, ComparableDoubleAdder> lines1 = service.getLines();
		Assert.assertNotNull(lines1);
		Assert.assertEquals(1, lines1.size());
		Assert.assertEquals(3.3, lines1.get("12345").doubleValue(), DELTA);

		service.add("54321", 5.5);
		final LinkedHashMap<String, ComparableDoubleAdder> lines2 = service.getLines();
		Assert.assertNotNull(lines2);
		Assert.assertEquals(2, lines2.size());
		Assert.assertEquals(3.3, lines2.get("12345").doubleValue(), DELTA);
		Assert.assertEquals(5.5, lines2.get("54321").doubleValue(), DELTA);

		service.add("98765", 4.4);
		final LinkedHashMap<String, ComparableDoubleAdder> lines3 = service.getLines();
		final Iterator<Map.Entry<String, ComparableDoubleAdder>> iterator = lines3.entrySet().iterator();
		Assert.assertEquals(3.3, iterator.next().getValue().doubleValue(), DELTA);
		Assert.assertEquals(4.4, iterator.next().getValue().doubleValue(), DELTA);
		Assert.assertEquals(5.5, iterator.next().getValue().doubleValue(), DELTA);
		Assert.assertFalse(iterator.hasNext());
	}

}
