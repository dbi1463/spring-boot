package tw.exmaple;

import static java.util.concurrent.CompletableFuture.anyOf;
import static java.util.concurrent.CompletableFuture.supplyAsync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tw.exmaple.core.Advertisement;
import tw.exmaple.repository.AdvertisementRepository;
import tw.exmaple.service.AdvertisementSource;

@Component
public class AdvertisementBiddingSystem {

	@Autowired
	@Qualifier("tenMaxSource")
	private AdvertisementSource tenMaxSource;

	@Autowired
	@Qualifier("mockSource")
	private AdvertisementSource mockSource;

	@Autowired
	private AdvertisementRepository repository;

	@Scheduled(fixedRate = 1000 * 5)
	public void refreshAdvertisement() {
		// TODO: add timeout
		anyOf(supplyAsync(tenMaxSource::getAdvertisement), supplyAsync(mockSource::getAdvertisement))
			.whenCompleteAsync((Object ad, Throwable e) -> {
				System.out.println(String.format("ad: %s, e: %s", ad, e));
				if (e == null && ad != null && ad instanceof Advertisement) {
					String title = ((Advertisement)ad).getTitle();
					if (!repository.exists(title)) {
						repository.save((Advertisement)ad);
					}
				}
			});
	}
}
