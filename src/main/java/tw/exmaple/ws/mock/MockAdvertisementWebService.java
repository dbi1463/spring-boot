package tw.exmaple.ws.mock;

import static tw.exmaple.util.ThreadUtils.sleepSilently;

import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockAdvertisementWebService {

	private Random random = new Random();

	@RequestMapping(value = "/supply/mobile/native/rmax-ad", method = RequestMethod.GET)
	public String getAdvertisement(@RequestParam(required = false) String rmaxSpaceId, @RequestParam(required = false) String dpid, @RequestParam(name = "v", required = false) int version) {
		if (random.nextBoolean()) {
			sleepSilently(10 * 1000);
		}
		return "{}";
	}
}
