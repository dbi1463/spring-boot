package tw.example;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;

import tw.example.core.Advertisement;
import tw.example.repository.AdvertisementRepository;
import tw.example.service.AdvertisementSource;

public class AdvertisementBiddingSystemTests {

	private AdvertisementRepository repository;
	private AdvertisementSource tenMaxSource;
	private AdvertisementSource mockSource;
	private AdvertisementBiddingSystem testee;

	@Before
	public void setUp() {
		repository = mock(AdvertisementRepository.class);
		testee = new AdvertisementBiddingSystem();
		testee.setRepository(repository);
	}

	@Test
	public void testRefreshAdvertisementFromTextMaxSource() throws InterruptedException, ExecutionException {
		Advertisement ad1 = new Advertisement();
		Advertisement ad2 = new Advertisement();
		tenMaxSource = new MockAdvertisementSource("tenMax", 0, ad1);
		mockSource = new MockAdvertisementSource("mock", 1000, ad2);
		testee.setTenMaxSource(tenMaxSource);
		testee.setMockSource(mockSource);
		testee.setTimeout(500);
		testee.refreshAdvertisement().get();
		verify(repository, times(1)).save(ad1);
		verify(repository, never()).save(ad2);
	}

	@Test
	public void testRefreshAdvertisementFromMockSource() throws InterruptedException, ExecutionException {
		Advertisement ad1 = new Advertisement();
		Advertisement ad2 = new Advertisement();
		tenMaxSource = new MockAdvertisementSource("tenMax", 1000, ad1);
		mockSource = new MockAdvertisementSource("mock", 0, ad2);
		testee.setTenMaxSource(tenMaxSource);
		testee.setMockSource(mockSource);
		testee.setTimeout(500);
		testee.refreshAdvertisement().get();
		verify(repository, times(1)).save(ad2);
		verify(repository, never()).save(ad1);
	}

	@Test
	public void testRefreshAdvertisementTimeout() throws InterruptedException, ExecutionException {
		Advertisement ad = new Advertisement();
		tenMaxSource = new MockAdvertisementSource("tenMax", 1000, ad);
		mockSource = new MockAdvertisementSource("mock", 1000, null);
		testee.setTenMaxSource(tenMaxSource);
		testee.setMockSource(mockSource);
		testee.setTimeout(500);
		try {
			testee.refreshAdvertisement().get();
		}
		catch (ExecutionException e) {
			assertEquals(TimeoutException.class, e.getCause().getClass());
		}
		verify(repository, never()).save(ad);
	}

	@Test
	public void testRefreshAdvertisementWithExistingAd() throws InterruptedException, ExecutionException {
		Advertisement ad = new Advertisement();
		ad.setTitle("existing");
		tenMaxSource = new MockAdvertisementSource("tenMax", 100, ad);
		mockSource = new MockAdvertisementSource("mock", 1000, null);
		testee.setTenMaxSource(tenMaxSource);
		testee.setMockSource(mockSource);
		testee.setTimeout(500);
		stub(repository.exists("existing")).toReturn(Boolean.TRUE);
		testee.refreshAdvertisement().get();
		verify(repository, never()).save(ad);
	}
}
