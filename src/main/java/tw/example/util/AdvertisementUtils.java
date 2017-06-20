package tw.example.util;

import static tw.example.util.JsonUtils.getElement;
import static tw.example.util.JsonUtils.getElements;
import static tw.example.util.JsonUtils.getInteger;
import static tw.example.util.JsonUtils.getString;
import static tw.example.util.JsonUtils.parse;

import java.util.List;

import com.google.gson.JsonElement;

import tw.example.core.Advertisement;

public class AdvertisementUtils {

	public static Advertisement toAdvertisement(final String response) {
		JsonElement json = parse(response);
		JsonElement root = getElement(json, "native");
		if (root != null) {
			List<JsonElement> assets = getElements(root, "assets");
			String title = getAssetString(assets, 1, "title", "text");
			if (title != null) {
				Advertisement advertisement = new Advertisement();
				advertisement.setTitle(title);
				advertisement.setDescription(getAssetString(assets, 4, "data", "value"));
				advertisement.setImageUrl(getAssetString(assets, 6, "img", "url"));
				advertisement.setIconUrl(getAssetString(assets, 9, "img", "url"));
				advertisement.setClickUrl(getString(getElement(root, "link"), "url"));
				List<JsonElement> impressions = getElements(root, "impressionEvent");
				if (!impressions.isEmpty()) {
					advertisement.setImpressionLink(impressions.get(0).getAsString());
				}
				return advertisement;
			}
		}
		return null;
	}

	private static String getAssetString(final List<JsonElement> assets, final int id, final String objectKey, final String valueKey) {
		for (JsonElement asset : assets) {
			if (getInteger(asset, "id") == id) {
				return getString(getElement(asset, objectKey), valueKey);
			}
		}
		return null;
	}
}
