package tw.example.service;

import static tw.example.util.AdvertisementUtils.toAdvertisement;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import tw.example.core.Advertisement;

public class AdvertisementSource {

	private String sourceUrl;

	public AdvertisementSource(String url) {
		sourceUrl = url;
	}

	public Advertisement getAdvertisement() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(sourceUrl);
		builder.queryParam("rmaxSpaceId", "55ba76bca772421f");
		builder.queryParam("dpid", "bd4b9b7903cf40ce");
		builder.queryParam("v", "1");
		RestTemplate template = new RestTemplate();
		String result = template.getForObject(builder.build().toUriString(), String.class);
		return toAdvertisement(result);
	}
}
