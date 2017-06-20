package tw.example.ws.mock;

import static tw.example.util.ThreadUtils.sleepSilently;

import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.example.util.IOUtils;

@RestController
public class MockAdvertisementWebService {

	private long sleepTime;

	private Random random;
	private BooleanRandom booleanRandom;

	public MockAdvertisementWebService() {
		random = new Random();
		setSleepTime(10 * 1000);
		setBooleanReandom(() -> random.nextBoolean());
	}

	@RequestMapping(value = "/supply/mobile/native/rmax-ad", method = RequestMethod.GET)
	public String getAdvertisement(@RequestParam(required = false) final String rmaxSpaceId, @RequestParam(required = false) final String dpid, @RequestParam(name = "v", required = false) final int version) {
		if (booleanRandom.nextBoolean()) {
			return IOUtils.toString(getClass().getResourceAsStream("response.json"));
		}
		sleepSilently(sleepTime);
		return "{}";
	}

	void setSleepTime(final long time) {
		sleepTime = time;
	}

	void setBooleanReandom(final BooleanRandom random) {
		booleanRandom = random;
	}
}
