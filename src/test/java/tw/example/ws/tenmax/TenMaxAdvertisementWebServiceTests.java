package tw.example.ws.tenmax;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tw.example.core.Advertisement;
import tw.example.repository.AdvertisementRepository;

public class TenMaxAdvertisementWebServiceTests {

	private AdvertisementRepository repository;
	private TenMaxAdvertisementWebService testee;

	@Before
	public void setUp() {
		repository = mock(AdvertisementRepository.class);
		testee = new TenMaxAdvertisementWebService();
		testee.setRepository(repository);
	}

	@Test
	public void testFindAdvertisements() {
		Advertisement ad1 = new Advertisement();
		Advertisement ad2 = new Advertisement();
		Advertisement ad3 = new Advertisement();

		stub(repository.findByTitleContaining("a")).toReturn(Arrays.asList(ad1, ad2));
		stub(repository.findByTitleContaining("b")).toReturn(Arrays.asList(ad1));
		stub(repository.findAll()).toReturn(Arrays.asList(ad1, ad2, ad3));

		List<Advertisement> result = testee.findAdvertisements("a");
		assertEquals(2, result.size());
		assertEquals(ad1, result.get(0));
		assertEquals(ad2, result.get(1));

		result = testee.findAdvertisements("b");
		assertEquals(1, result.size());
		assertEquals(ad1, result.get(0));

		result = testee.findAdvertisements("");
		assertEquals(3, result.size());
		assertEquals(ad1, result.get(0));
		assertEquals(ad2, result.get(1));
		assertEquals(ad3, result.get(2));
	}
}
