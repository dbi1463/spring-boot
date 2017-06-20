package tw.example;

import tw.example.core.Advertisement;
import tw.example.service.AdvertisementSource;
import tw.example.util.ThreadUtils;

public class MockAdvertisementSource extends AdvertisementSource {

	private long processTime;
	private Advertisement advertisement;

	public MockAdvertisementSource(final String url, final long expectedProcessTime, final Advertisement expectedAdvertisement) {
		super(url);
		processTime = expectedProcessTime;
		advertisement = expectedAdvertisement;
	}

	@Override
	public Advertisement getAdvertisement() {
		if (processTime > 0) {
			ThreadUtils.sleepSilently(processTime);
		}
		return advertisement;
	}
}
