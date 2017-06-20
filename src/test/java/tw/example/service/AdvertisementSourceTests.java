package tw.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import tw.example.util.IOUtils;

public class AdvertisementSourceTests {

	private MockWebServer webServer;
	private AdvertisementSource testee;

	@Before
	public void setUp() {
		webServer = new MockWebServer();
		HttpUrl url = webServer.url("/supply/mobile/native/rmax-ad");
		testee = new AdvertisementSource(url.toString());
	}

	@After
	public void tearDown() throws IOException {
		webServer.shutdown();
	}

	@Test
	public void testGetAdvertisement() {
		webServer.enqueue(new MockResponse().setBody(IOUtils.toString(getClass().getResourceAsStream("response.json"))));
		assertEquals("//tenmaximg.cacafly.net/upload/7/7/7/1/9647e2ee.png?v=2", testee.getAdvertisement().getImageUrl());
	}

	@Test
	public void testGetAdvertisementWithEmptyBody() {
		webServer.enqueue(new MockResponse().setBody("{}"));
		assertNull(testee.getAdvertisement());
	}
}
