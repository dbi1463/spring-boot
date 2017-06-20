package tw.example;

import static java.util.concurrent.CompletableFuture.anyOf;
import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tw.example.core.Advertisement;
import tw.example.repository.AdvertisementRepository;
import tw.example.service.AdvertisementSource;

@Component
public class AdvertisementBiddingSystem {

	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	@Autowired
	@Qualifier("tenMaxSource")
	private AdvertisementSource tenMaxSource;

	@Autowired
	@Qualifier("mockSource")
	private AdvertisementSource mockSource;

	@Autowired
	private AdvertisementRepository repository;

	private long timeout = 5 * 100;

	@Scheduled(fixedRate = 1000 * 20)
	public void refreshAdvertisement() {
		anyOf(supplyAsync(tenMaxSource::getAdvertisement), supplyAsync(mockSource::getAdvertisement), failAfter(timeout))
			.whenCompleteAsync((final Object ad, final Throwable e) -> {
				System.out.println(String.format("ad: %s, e: %s", ad, e));
				if (e == null && ad != null && ad instanceof Advertisement) {
					String title = ((Advertisement)ad).getTitle();
					if (!repository.exists(title)) {
						repository.save((Advertisement)ad);
					}
				}
			}
		);
	}

	<T> CompletableFuture<T> failAfter(final long time) {
		final CompletableFuture<T> promise = new CompletableFuture<>();
		scheduler.schedule(() -> {
			final TimeoutException exception = new TimeoutException();
			return promise.completeExceptionally(exception);
		}, time, TimeUnit.MILLISECONDS);
		return promise;
	}
}
