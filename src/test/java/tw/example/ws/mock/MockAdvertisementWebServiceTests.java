package tw.example.ws.mock;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tw.example.util.IOUtils;


public class MockAdvertisementWebServiceTests {

	@Test
	public void testGetAdvertisement() {
		MockAdvertisementWebService testee = new MockAdvertisementWebService();
		testee.setBooleanReandom(() -> true);

		assertEquals(IOUtils.toString(getClass().getResourceAsStream("response.json")), testee.getAdvertisement("55ba76bca772421f", "bd4b9b7903cf40ce", 1));
	}

	@Test
	public void testGetAdvertisementTiemout() {
		MockAdvertisementWebService testee = new MockAdvertisementWebService();
		testee.setBooleanReandom(() -> false);
		testee.setSleepTime(500);

		long start = currentTimeMillis();
		assertEquals("{}", testee.getAdvertisement("55ba76bca772421f", "bd4b9b7903cf40ce", 1));
		assertTrue((currentTimeMillis() - start) >= 500);
	}
}
